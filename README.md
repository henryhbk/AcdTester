# ACDTester Inersource Project
THis project is for using the MIMIC III database for testing/traing ACD (The annotator for Clinical Data). It is a Maven based project and will pull in both the IBM FHIR server along with the ACD Java APIs. The MIMIC III data is represented in a HIbernate 5 entity set and each of the various tables have hibernate entities that represent them. The entities (and database) have been enriched with syntheic information to make the database more usable (such as all patients have names/addresses and social determinants).

## For Contributors/Consumers
* [Code of Conduct](CODE_OF_CONDUCT.md)
* [Contribution Guidance](CONTRIBUTING.md)
* [Support Documents](SUPPORT.md)
* [The Role of the Maintainer](guides/Maintainer.md)
* [InnerSource README Template](InnerSource_README_Template.md)
* [GitHub Workflow](guides/GitHub_Workflow.md)
* [Test Guidance Template](guides/testing.md)
* [Issue and Pull Request Template](.github/)

## Communication
This project is maintained by Henry Feldman (henry.feldman@ibm.com). Email/Slack me with questions

## Synthetic enhancements
All of this social data is synthetic and is randomly assigned based on census data. The addresses are realistic and as such sould pass any validation tool (such as the city/state/zip combo are valid, but the street names are purely random from the most common street names in the US. The real database of course is mostly MA based patients (since BIDMC is in Boston), however the synthetic data uses patients all over the US. Information such as language, ethnicity, Race are all pulled from the MIMIC data to be consistent with the notes, etc for that patient, however the name and other synthetic data may not match (for instance if the patient is listed in MIMIC has being japanese and only speaking japanese, the system may have named him billy-bob johnson rnadomly).

## Compliance changes
This database is not exactly the same (other than the synthetic information above) due to IBM legal's determination that MIMIC III as is does not exactly comply with HIPAA's Safe Harbo rules due to the preservation of day-of-the-week in their magical calendar math that moved all dates several centuries in the future to preserve day of the week. Now while the day of the week is important for doing hospital based research (questions like "do patients get more opioids on a friday night?" need this information) they aren't particularly important for NLP. As such we determined the easiest thing was to completely randomize the dates. Now of course we can't simply replaces all dates in the database with arbitrary random dates or you have data which simply makes no sense (like orders succeeding reports, and the like). So we randomized in a hierarchical fashion. So things like admission dates are completely random (and now take place in the past! so your date validation routines should work, and dates are now within the Java Date epoch range) Once the random admission date, all entities that belong to an admission (labs, prescriptions, notes) are all moved to the same relative sequence, so you should see things proceeding in a logical fashion in general.

## Data
So you will see some "extras" I left in for you, including many of the entities (such as prescriptions - medication orders, labs, etc) have a FHIR-json representation in a column stores as text. This lets you rapidly stream the contents of that table out in FHIR to stress test some system that needs bombardment with labs/patients/medication orders, etc. The fhir was created by the IBM FHir Server R4 api.
In the data You will find around 27m labs, 3m medications, 1.3m notes
Now when selecting data, I added an extra flag which on notes, medications and labs you will find a boolean flag for acd_study they will pull up doctor admission notes (if you want there are all the ancillary service notes, such as PT, nursing, etc but if all you want are doctor notes, you can greatly reduce the data set size down to around 11,000 curated notes.

In addition with a massive effort I enhanced the Prescriptions table with RxNorm IDs (MIMIC only has FDB and BIDMC's internal mapping codes). This was all done via the NLM RxNav API.

# Data Facts

| Element | Quantity | Fhir Json | Synthetic Enhanced
------------ | ------------- | ------------- | -------------
| Patients | 46,000 | Yes | Yes
| Prescriptions (medication order) | 6m | Yes | Yes
| Caregivers (doctors/nurses/etc) | 7,500 | Yes | Yes
| Labs | 27m | Yes | No
| Notes | 1.2m | Yes | No
| Radiology Reports | 430,000 | Yes | No
| Echo Reports | 46,000 | Yes | No
| EKG Reports | 205,000 | Yes | No
| Admissions | 59,000 | Yes | No
| Chart Events (vitals, etc) | 322m | Yes | No

## Why Hibernate (and CriteriaQueries)
For those who have never used an ORM, but have experienced the living hell of a multi-developer project with embedded SQL code you've encountered the fun where the source code and table structure rapidly become out of synch. So by ising criteria query, the queries and entities use the java fields (you can forget the data is stored in a SQL database, you talk as if they are in your project and are just regular java objects). All of this magic is accomplished by annotations on the java classes, which is where columns of the database are tied to the java fields. Now if you *have* used hibernate way in the past, you experienced XML file hell where hundreds of files all over the place were required. That's all gone and now uses regular JPA 2 annotations. If you look at the Patients.java class you will see the hibernate annotations on the fields. The cool thing is if tomorrow you suddenly needed to move this MySQL database to DB2 it takes 2 minutes to do so. I know because I did it (in fact I left the DB2 stuff in place so you can actually have both at the same time). If you are the type to throw caution to the wind you can even set hibernate to automagically alter the database structure when you add fields (don't be a jerk this is a shared resource, don't delete/alter it!)

## Notes on using DB2
OK so if you decide your project needs DB2 this project is all set and includes a DB2 shadow ORM layer. You will find a similar config file */AcdTester/src/main/resources/hibernate-db2.cfg.xml* . That file is for a separate Db2 instance (you can *in theory* read from one and write to the other). Note this is the COMMUNITY EDITION (CE) dialect of DB2. I discovered that CE and Z have slight differences (yay?). Like commands such as "BEGIN TRANSACTION" only work on Z, so that required some tweaks.

# Getting Started
So other than the usual importing the project to start your project in eclipse, wait for Maven to import all the appropriate dependencies. Assuming that all works you are ready to get started. But the database needs to be connected. You will need to update the hibernate connection information in **hibernate.cfg.xml** to the appropriate server username/PW as required.

You will find all the hibernate routines represented with the interfaces in *com.ibm.nlp.services* and then the DAOs themselves are in *com.ibm.nlp.hibernate*. So a typical implentation might be like:

  **private NoteEventService noteEventDao = new NoteEventDao();**
  
  These DAO classes are all threadsafe. All the Entities are in *com.ibm.nlp.model.mimic3* and are both Hibernate annotated and JSON annotated (for Jackson), so it is fairly easy to create RESTful services that need to send these back.
  
  Simple usage includes using the DAOs directly to fetch lists:
  
  > for (Prescription prescription : prescriptionDao.getAllPrescriptions()) {
  >     do some stuff
  > }

Be aware some of these calls can return massive quantities of data (such as getAllLabs is many gigabytes of information and can break Java's garbage collection) So for that one we have a paged version that allows sequential fetches.

## Prerequisites
> Java 8 (if you want later than 8, I assume you know how to deal with Java version issues)
> Maven
If you aren't sure what those are, you probably shouldn't be checking this code out without talking with someone who knows how to set up a development project.
> This project is really set for Eclipse
> In Eclipse: optionally turn on the JPA facet if that works for you. I didn't use that.

## Installing
After checking the project out, bring it into eclipse (you can skip this if you directly checked out this project inside eclipse). While in theory you can import this code into an existing project (who enjoys all those broken links/pacakge markings). So in general I assume you are bringing this code into a *NEW PROJECT*. Once that happens, expect a few moments while Maven goes out and pulls in all the dependcies and compiles the project. A few times I have had to force both a Maven Update (right click on the project maven->Maven Clean) or a full prject-clean. In theory if the project all imported fine, no red icons have appeared and you are now seeing a lot of code.

At this point you MUST setup the database/hibernate or nothing will work (you simply can't launch anything in this project or the hibernate integrity checker will yell at you)

## Setting up the database access (i.e. Hibernate)
This project uses Hibernate 5 to bring handle all the database access (as an ORM). The access is managed by a single configuration file called hibernate.cfg.xml. This file is the bane of your existence. It is located in src/main/resources, and like the dude in the matrix, you have to look at in code (personally I find the xml easier to read). There are 3 fields you should change (and basically nothing else unless you understand hibernate).
* First the server name needs to be the server name inside IBM
* Username (your username)
* Password (your password - yes this is somewhat ugly, which is why your mysql info should not be your actual IBM password)
* Save and get ready to test.

## Test and Tutorial run
# Initial Setup
Before being able to use the tutorial, you need to do some basic setup for the database connection and the ACD connection. The first file to edit is "hibernate.cfg.xml". You will need to set your MySQL URL, username and password. Important to not mess with ANY settings below the password line (# ESPECIALLY DO NOT TURN ON AUTOMATIC SCHEMA UPDATES #, or edits in your code can try to alter the database schema (breaking everyone else). For debugging you can turn on show_sql, in which case your console will fill up with giant piles of SQL;

Next item to edit will be the ACD configuration, which is helpfully stored in a class called the com.ibm.nlp.AcdConfigurator. This is a simple Util class which has a single method to return an ACD service. So you will need your API key (replace the ACD_KEY value with your API_key.

# Tutorial/First Run
There is a class inside the default package of the project (should be the first element, or search with cmd-T) for FirstRunTutorial.java. Inside is a quick demo-test where it will pull up some clinical data, and you can see if this works.  The tutorial is also a good check that you got it all configured correctly since it will both access MySQL and ACD. Right click on the file and select Run As->Java Application. On the console you should see some patient information print out:

````
Patient: id: 110
First:  Arthur
Last: Wheeler
dob: 1911-05-29 (109y) (M)
Address: 799 River St. 
Suncook, NH 03275

PATIENT MEDICATION ORDERS:

0. NEO*IV*Ampicillin Sodium 500mg IV
1. NEO*IV*Gentamicin 14mg IV
2. NEO*IV*Acyclovir 70mg IV
3. Send 500mg Vial 1VIAL IV
4. Syringe (Neonatal) *D5W* 2.4ml IV
5. Syringe (Neonatal) *D5W* 12ml IV
6. Heparin Sodium (Preservative Free) 5UNIT IV
7. Pediatric Vitamins ADC 1ml PO

Example FHIR resource cache: example Patient:
      [large block of FHIR JSON]
      
Analyzing note row_id = 1 with ACD
attribute: headache
attribute: stiff neck
attribute: Invasive procedure
[very long list of attributes found in the note]
      
````
## Next Steps
OK so now you want to actually do something with MIMIC III data (note this is the V1.4 version). The services in com.ibm.nlp.services are the interface for all the data access objects for each hibernate entity (i.e. notes) For your sanity I would add methods in there. The entities themselves are all in com.ibm.nlp.model.mimic3. And for various boring reasons I kept the names the same as the table they represent, and many of the field names in the entities match the database (this is a nice safety feature in case you miss an annotation, hibernate will assume there is an identical name for the database column). Note that MySQL is not case-sensitive in queries (but does remember case). if you for some reason want to switch the model to DB2 (I have pre-built that infrastructure into the framework) remember that DB2 *is* case sensitive.

In general you can instantiate the DAO classes whereever you want, but particularly if you are doing a big thread pool operation I would create one and reference it in all the threads. (there are several example thread classes you can look at that do this). The DAO classes are fully thread-safe, and can either be static or dynamically instantiated.

All the entities use basic getters/setters (which Hibernate requires since it uses reflection to map into the entity), so you can directly access any data element. All the entities have jackson JSON annotations @JsonProperty and @JsonSetter on the getters and setters respectively.

Given the scale of the data the entities do not perform the joins (i.e. pull in related entities via foreign keys) as imagine the memory footprint of 27m labs if we also pulled in admissions and patient records together. But is trivial to add those (be aware of the RAM usage and performance implications of joins.

# live patient lookup
Of note, one of the special services (for demo purposes mostly) is the ability to do live patient searches. This is used to feed an oracle type field via a web service. The PatientDao class has a method called *liveOracleMultiFieldsSearchForPatients* which takes a list of key-value pairs to search many fields of the patient record. So you can start immediately pulling patient records as the person types (note as you can imagine this can be a performance drain). This technique works well in real clinical use (the EMR I built could do this across millions of patients in 2 languages even). So as you type J-O every patient who has JO in their name will start showing (last, first, etc) so John, Jones, Jose all will be found. The return object is a subset of Patients (just with a few fields of the patients to throw across the web) (note hibernate entities can represent a small subset of a table), once you select one then you select the patient from the SearchPatient class's id.
