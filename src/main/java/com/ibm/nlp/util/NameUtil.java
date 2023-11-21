package com.ibm.nlp.util;

import java.util.Random;

/**
 * The Class Names.
 *
 * @author henry.feldman@ibm.com
 */
public class NameUtil {

	/** The males. */
	public static String[] MALES = { "James", " John", " Robert", " Michael", " William", " David", " Joseph",
			" Richard", " Charles", " Thomas", " Christopher", " Daniel", " Matthew", " George", " Anthony", " Donald",
			" Paul", " Mark", " Andrew", " Edward", " Steven", " Kenneth", " Joshua", " Kevin", " Brian", " Ronald",
			" Timothy", " Jason", " Jeffrey", " Ryan", " Jacob", " Frank", " Gary", " Nicholas", " Eric", " Stephen",
			" Jonathan", " Larry", " Justin", " Raymond", " Scott", " Samuel", " Brandon", " Benjamin", " Gregory",
			" Jack", " Henry", " Patrick", " Alexander", " Walter", " Dennis", " Jerry", " Tyler", " Aaron", " Peter",
			" Jose", " Douglas", " Adam", " Harold", " Nathan", " Zachary", " Arthur", " Carl", " Albert", " Kyle",
			" Lawrence", " Joe", " Willie", " Gerald", " Roger", " Jeremy", " Keith", " Ethan", " Terry", " Christian",
			" Harry", " Jesse", " Sean", " Ralph", " Austin", " Roy", " Louis", " Noah", " Bruce", " Billy", " Bryan",
			" Eugene", " Jordan", " Dylan", " Russell", " Wayne", " Alan", " Juan", " Gabriel", " Howard", " Fred",
			" Vincent", " Philip", " Logan", " Randy", " Victor", " Bobby", " Johnny", " Phillip", " Martin",
			" Bradley", " Shawn", " Clarence", " Travis", " Ernest", " Stanley", " Craig", " Elijah", " Leonard",
			" Carlos", " Earl", " Jimmy", " Francis", " Cody", " Caleb", " Mason", " Danny", " Isaac", " Todd",
			" Cameron", " Dale", " Luis", " Alex", " Luke", " Nathaniel", " Allen", " Joel", " Evan", " Frederick",
			" Curtis", " Lucas", " Norman", " Marvin", " Tony", " Antonio", " Glenn", " Rodney", " Theodore", " Melvin",
			" Alfred", " Edwin", " Steve", " Chad", " Jackson", " Adrian", "Junior", "Wei", "Hiroshi", "Do Yoo",
			"Mohammed", "Jose", "Hugo", "Francesco" };

	/** The females. */
	public static String[] FEMALES = { "Mary", " Elizabeth", " Patricia", " Jennifer", " Linda", " Barbara",
			" Margaret", " Susan", " Dorothy", " Sarah", " Jessica", " Helen", " Nancy", " Betty", " Karen", " Lisa",
			" Anna", " Sandra", " Emily", " Ashley", " Kimberly", " Donna", " Ruth", " Carol", " Michelle", " Laura",
			" Amanda", " Melissa", " Rebecca", " Deborah", " Stephanie", " Sharon", " Kathleen", " Cynthia", " Amy",
			" Shirley", " Emma", " Angela", " Catherine", " Virginia", " Katherine", " Brenda", " Pamela", " Frances",
			" Nicole", " Christine", " Samantha", " Evelyn", " Rachel", " Alice", " Janet", " Carolyn", " Debra",
			" Martha", " Maria", " Marie", " Heather", " Diane", " Julie", " Joyce", " Grace", " Victoria", " Rose",
			" Joan", " Christina", " Kelly", " Ann", " Lauren", " Doris", " Julia", " Jean", " Judith", " Olivia",
			" Kathryn", " Mildred", " Lillian", " Cheryl", " Megan", " Hannah", " Andrea", " Sara", " Jacqueline",
			" Teresa", " Gloria", " Janice", " Theresa", " Judy", " Madison", " Beverly", " Denise", " Jane",
			" Marilyn", " Amber", " Danielle", " Abigail", " Charlotte", " Diana", " Brittany", " Natalie", " Irene",
			" Ruby", " Annie", " Sophia", " Lori", " Kayla", " Alexis", " Tiffany", " Florence", " Isabella", " Kathy",
			" Louise", " Lois", " Tammy", " Crystal", " Bonnie", " Phyllis", " Anne", " Taylor", " Erin", " Josephine",
			" Alyssa", " Ella", " Allison", " Shannon", " Edna", " Peggy", " Tina", " Robin", " Dawn", " Eleanor",
			" Rita", " Audrey", " Wanda", " Clara", " Ethel", " Paula", " Norma", " Ellen", " Marjorie", " Jamie",
			" Leslie", " Edith", " Connie", " Eva", " Gladys", " Carrie", " Ava", " Wendy", " Hazel", " Valerie",
			"Olivia", "Louise", "Lucia", "Sofia", "Sofie", "Emilía", "Maria", "Precious", "Li", "Nozomi", "Ha Yoon",
			"Ayesha", "Tamar" };

	/** The lastnames. */
	public static String[] LASTNAMES = { "Powell", "Jenkins", "Perry", "Russell", "Sullivan", "Bell", "Coleman",
			"Butler", "Henderson", "Barnes", "Gonzales", "Fisher", "Vasquez", "Simmons", "Romero", "Jordan",
			"Patterson", "Alexander", "Hamilton", "Graham", "Reynolds", "Griffin", "Wallace", "Moreno", "West", "Cole",
			"Hayes", "Bryant", "Herrera", "Gibson", "Ellis", "Tran", "Medina", "Aguilar", "Stevens", "Murray", "Ford",
			"Castro", "Marshall", "Owens", "Harrison", "Fernandez", "Mcdonald", "Woods", "Washington", "Kennedy",
			"Wells", "Vargas", "Henry", "Chen", "Freeman", "Webb", "Tucker", "Guzman", "Burns", "Crawford", "Olson",
			"Simpson", "Porter", "Hunter", "Gordon", "Mendez", "Silva", "Shaw", "Snyder", "Mason", "Dixon", "Munoz",
			"Hunt", "Hicks", "Holmes", "Palmer", "Wagner", "Black", "Robertson", "Boyd", "Rose", "Stone", "Salazar",
			"Fox", "Warren", "Mills", "Meyer", "Rice", "Schmidt", "Garza", "Daniels", "Ferguson", "Nichols", "Stephens",
			"Soto", "Weaver", "Ryan", "Gardner", "Payne", "Grant", "Dunn", "Kelley", "Spencer", "Hawkins", "Arnold",
			"Pierce", "Vazquez", "Hansen", "Peters", "Santos", "Hart", "Bradley", "Knight", "Elliott", "Cunningham",
			"Duncan", "Armstrong", "Hudson", "Carroll", "Lane", "Riley", "Andrews", "Alvarado", "Ray", "Delgado",
			"Berry", "Perkins", "Hoffman", "Johnston", "Matthews", "Pena", "Richards", "Contreras", "Willis",
			"Carpenter", "Lawrence", "Sandoval", "Guerrero", "George", "Chapman", "Rios", "Estrada", "Ortega",
			"Watkins", "Greene", "Nunez", "Wheeler", "Valdez", "Harper", "Burke", "Larson", "Santiago", "Maldonado",
			"Morrison", "Franklin", "Carlson", "Austin", "Dominguez", "Carr", "Lawson", "Jacobs", "Obrien", "Lynch",
			"Singh", "Vega", "Bishop", "Montgomery", "Oliver", "Jensen", "Harvey", "Williamson", "Gilbert", "Dean",
			"Sims", "Espinoza", "Howell", "Li", "Wong", "Reid", "Hanson", "Le", "Mccoy", "Garrett", "Burton", "Fuller",
			"Wang", "Weber", "Welch", "Rojas", "Lucas", "Marquez", "Fields", "Park", "Yang", "Little", "Banks",
			"Padilla", "Day", "Walsh", "Bowman", "Schultz", "Luna", "Fowler", "Mejia", "Davidson", "Acosta", "Brewer",
			"May", "Holland", "Juarez", "Newman", "Pearson", "Curtis", "Cortez", "Douglas", "Schneider", "Joseph",
			"Barrett", "Navarro", "Figueroa", "Keller", "Avila", "Wade", "Molina", "Stanley", "Hopkins", "Campos",
			"Barnett", "Bates", "Chambers", "Caldwell", "Beck", "Lambert", "Miranda", "Byrd", "Craig", "Ayala", "Lowe",
			"Frazier", "Powers", "Neal", "Leonard", "Gregory", "Carrillo", "Sutton", "Fleming", "Rhodes", "Shelton",
			"Schwartz", "Norris", "Jennings", "Watts", "Duran", "Walters", "Cohen", "Mcdaniel", "Moran", "Parks",
			"Steele", "Vaughn", "Becker", "Holt", "Deleon", "Barker", "Terry", "Hale", "Leon", "Hail", "Benson",
			"Haynes", "Horton", "Miles", "Lyons", "Pham", "Graves", "Bush", "Thornton", "Wolfe", "Warner", "Cabrera",
			"Mckinney", "Mann", "Zimmerman", "Dawson", "Lara", "Fletcher", "Page", "Mccarthy", "Love", "Robles",
			"Cervantes", "Solis", "Erickson", "Reeves", "Chang", "Klein", "Salinas", "Fuentes", "Baldwin", "Daniel",
			"Simon", "Velasquez", "Hardy", "Higgins", "Aguirre", "Lin", "Cummings", "Chandler", "Sharp", "Barber",
			"Bowen", "Ochoa", "Dennis", "Robbins", "Liu", "Ramsey", "Francis", "Griffith", "Paul", "Blair", "Oconnor",
			"Cardenas", "Pacheco", "Cross", "Calderon", "Quinn", "Moss", "Swanson", "Chan", "Rivas", "Khan", "Rodgers",
			"Serrano", "Fitzgerald", "Rosales", "Stevenson", "Christensen", "Manning", "Gill", "Curry", "Mclaughlin",
			"Harmon", "Mcgee", "Gross", "Doyle", "Garner", "Newton", "Burgess", "Reese", "Walton", "Blake", "Trujillo",
			"Adkins", "Brady", "Goodman", "Roman", "Webster", "Goodwin", "Fischer", "Huang", "Potter", "Delacruz",
			"Montoya", "Todd", "Wu", "Hines", "Mullins", "Castaneda", "Malone", "Cannon", "Tate", "Mack", "Sherman",
			"Hubbard", "Hodges", "Zhang", "Guerra", "Wolf", "Valencia", "Franco", "Saunders", "Rowe", "Gallagher",
			"Farmer", "Hammond", "Hampton", "Townsend", "Ingram", "Wise", "Gallegos", "Clarke", "Barton", "Schroeder",
			"Maxwell", "Waters", "Logan", "Camacho", "Strickland", "Norman", "Person", "Colon", "Parsons", "Frank",
			"Harrington", "Glover", "Osborne", "Buchanan", "Casey", "Floyd", "Patton", "Ibarra", "Ball", "Tyler",
			"Suarez", "Bowers", "Orozco", "Salas", "Cobb", "Gibbs", "Andrade", "Bauer", "Conner", "Moody", "Escobar",
			"Mcguire", "Lloyd", "Mueller", "Hartman", "French", "Kramer", "Mcbride", "Pope", "Lindsey", "Velazquez",
			"Norton", "Mccormick", "Sparks", "Flynn", "Yates", "Hogan", "Marsh", "Macias", "Villanueva", "Zamora",
			"Pratt", "Stokes", "Owen", "Ballard", "Lang", "Brock", "Villarreal", "Charles", "Drake", "Barrera", "Cain",
			"Patrick", "Pineda", "Burnett", "Mercado", "Santana", "Shepherd", "Bautista", "Ali", "Shaffer", "Lamb",
			"Trevino", "Mckenzie", "Hess", "Beil", "Olsen", "Cochran", "Morton", "Nash", "Wilkins", "Petersen",
			"Briggs", "Shah", "Roth", "Nicholson", "Holloway", "Lozano", "Flowers", "Rangel", "Hoover", "Arias",
			"Short", "Mora", "Valenzuela", "Bryan", "Meyers", "Weiss", "Underwood", "Bass", "Greer", "Summers",
			"Houston", "Carson", "Morrow", "Clayton", "Whitaker", "Decker", "Yoder", "Collier", "Zuniga", "Carey",
			"Wilcox", "Melendez", "Poole", "Roberson", "Larsen", "Conley", "Davenport", "Copeland", "Massey", "Lam",
			"Huff", "Rocha", "Cameron", "Jefferson", "Hood", "Monroe", "Anthony", "Pittman", "Huynh", "Randall",
			"Singleton", "Kirk", "Combs", "Mathis", "Christian", "Skinner", "Bradford", "Richard", "Galvan", "Wall",
			"Boone", "Kirby", "Wilkinson", "Bridges", "Bruce", "Atkinson", "Velez", "Meza", "Roy", "Vincent", "York",
			"Hodge", "Villa", "Abbott", "Allison", "Tapia", "Gates", "Chase", "Sosa", "Sweeney", "Farrell", "Wyatt",
			"Dalton", "Horn", "Barron", "Phelps", "Yu", "Dickerson", "Heath", "Foley", "Atkins", "Mathews", "Bonilla",
			"Acevedo", "Benitez", "Zavala", "Hensley", "Glenn", "Cisneros", "Harrell", "Shields", "Rubio", "Choi",
			"Huffman", "Boyer", "Garrison", "Arroyo", "Bond", "Kane", "Hancock", "Callahan", "Dillon", "Cline",
			"Wiggins", "Grimes", "Arellano", "Melton", "Oneill", "Savage", "Ho", "Beltran", "Pitts", "Parrish", "Ponce",
			"Rich", "Booth", "Koch", "Golden", "Ware", "Brennan", "Mcdowell", "Marks", "Cantu", "Humphrey", "Baxter",
			"Sawyer", "Clay", "Tanner", "Hutchinson", "Kaur", "Berg", "Wiley", "Gilmore", "Russo", "Villegas", "Hobbs",
			"Keith", "Wilkerson", "Ahmed", "Beard", "Mcclain", "Montes", "Mata", "Rosario", "Vang", "Walter", "Henson",
			"Oneal", "Mosley", "Mcclure", "Beasley", "Stephenson", "Snow", "Huerta", "Preston", "Vance", "Barry",
			"Johns", "Eaton", "Blackwell", "Dyer", "Prince", "Macdonald", "Solomon", "Guevara", "Stafford", "English",
			"Hurst", "Woodard", "Cortes", "Shannon", "Kemp", "Nolan", "Mccullough", "Merritt", "Murillo", "Moon",
			"Salgado", "Strong", "Kline", "Cordova", "Barajas", "Roach", "Rosas", "Winters", "Jacobson", "Lester",
			"Knox", "Bullock", "Kerr", "Leach", "Meadows", "Davila", "Orr", "Whitehead", "Pruitt", "Kent", "Conway",
			"Mckee", "Barr", "David", "Dejesus", "Marin", "Berger", "Mcintyre", "Blankenship", "Gaines", "Palacios",
			"Cuevas", "Bartlett", "Durham", "Dorsey", "Mccall", "Odonnell", "Stein", "Browning", "Stout", "Lowery",
			"Sloan", "Mclean", "Hendricks", "Calhoun", "Sexton", "Chung", "Gentry", "Hull", "Duarte", "Ellison",
			"Nielsen", "Gillespie", "Buck", "Middleton", "Sellers", "Leblanc", "Esparza", "Hardin", "Bradshaw",
			"Mcintosh", "Howe", "Livingston", "Frost", "Glass", "Morse", "Knapp", "Herman", "Stark", "Bravo", "Noble",
			"Spears", "Weeks", "Corona", "Frederick", "Buckley", "Mcfarland", "Hebert", "Enriquez", "Hickman",
			"Quintero", "Randolph", "Schaefer", "Walls", "Trejo", "House", "Reilly", "Pennington", "Michael", "Conrad",
			"Giles", "Benjamin", "Crosby", "Fitzpatrick", "Donovan", "Mays", "Mahoney", "Valentine", "Raymond",
			"Medrano", "Hahn", "Mcmillan", "Small", "Bentley", "Felix", "Peck", "Lucero", "Boyle", "Hanna", "Pace",
			"Rush", "Hurley", "Harding", "Mcconnell", "Bernal", "Nava", "Ayers", "Everett", "Ventura", "Avery", "Pugh",
			"Mayer", "Bender", "Shepard", "Mcmahon", "Landry", "Case", "Sampson", "Moses", "Magana", "Blackburn",
			"Dunlap", "Gould", "Duffy", "Vaughan", "Herring", "Mckay", "Espinosa", "Rivers", "Farley", "Bernard",
			"Ashley", "Friedman", "Potts", "Truong", "Costa", "Correa", "Blevins", "Nixon", "Clements", "Fry",
			"Delarosa", "Best", "Benton", "Lugo", "Portillo", "Dougherty", "Crane", "Haley", "Phan", "Villalobos",
			"Blanchard", "Horne", "Finley", "Quintana", "Lynn", "Esquivel", "Bean", "Dodson", "Mullen", "Xiong",
			"Hayden", "Cano", "Levy", "Huber", "Richmond", "Moyer", "Lim", "Frye", "Sheppard", "Mccarty", "Avalos",
			"Booker", "Waller", "Parra", "Woodward", "Jaramillo", "Krueger", "Rasmussen", "Brandt", "Peralta",
			"Donaldson", "Stuart", "Faulkner", "Maynard", "Galindo", "Coffey", "Estes", "Sanford", "Burch", "Maddox",
			"Vo", "Oconnell", "Vu", "Andersen", "Spence", "Mcpherson", "Church", "Schmitt", "Stanton", "Leal", "Cherry",
			"Compton", "Dudley", "Sierra", "Pollard", "Alfaro", "Hester", "Proctor", "Lu", "Hinton", "Novak", "Good",
			"Madden", "Mccann", "Terrell", "Jarvis", "Dickson", "Reyna", "Cantrell", "Mayo", "Branch", "Hendrix",
			"Rollins", "Rowland", "Whitney", "Duke", "Odom", "Daugherty", "Travis", "Tang", "Archer" };

	/** The streets. */
	public static String[] STREETS = { "Main St. ", "Church St. ", "Main St. North ", "Main St. South ", "Elm St. ",
			"High St. ", "Main St. West ", "Washington St. ", "Main St. East ", "Park Ave. ", "2nd St. ", "Walnut St. ",
			"Chestnut St. ", "Maple Ave. ", "Maple St. ", "BRd. St. ", "Oak St. ", "Center St. ", "Pine St. ",
			"River Rd. ", "Market St. ", "Water St. ", "Union St. ", "South St. ", "Park St. ", "3rd St. ",
			"Washington Ave. ", "Cherry St. ", "North St. ", "4th St. ", "Ct. St. ", "Highland Ave. ", "Mill St. ",
			"Franklin St. ", "Prospect St. ", "School St. ", "Spring St. ", "Central Ave. ", "1st St. ", "State St. ",
			"Front St. ", "West St. ", "Jefferson St. ", "Cedar St. ", "Jackson St. ", "Park Place ", "Bridge St. ",
			"Locust St. ", "Madison Ave. ", "Meadow Ln. ", "Spruce St. ", "Grove St. ", "Ridge Rd. ", "5th St. ",
			"Pearl St. ", "Lincoln St. ", "Madison St. ", "Dogwood Dr. ", "Lincoln Ave. ", "Pennsylvania Ave. ",
			"Pleasant St. ", "4th St. West ", "Adams St. ", "Jefferson Ave. ", "3rd St. West ", "7th St. ",
			"Academy St. ", "11th St. ", "2nd Ave. ", "East St. ", "Green St. ", "Hickory Ln. ", "Route 1 ",
			"Summit Ave. ", "Virginia Ave. ", "12th St. ", "5th Ave. ", "6th St. ", "9th St. ", "Charles St. ",
			"Cherry Ln. ", "Elizabeth St. ", "Hill St. ", "River St. ", "10th St. ", "Colonial Dr. ", "Monroe St. ",
			"Valley Rd. ", "Winding Way ", "1st Ave. ", "Fairway Dr. ", "Liberty St. ", "2nd St. West ", "3rd Ave. ",
			"BRd.way ", "Church Rd. ", "Delaware Ave. ", "Prospect Ave. ", "Route 30 ", "Sunset Dr. ", "Vine St. ",
			"Woodland Dr. ", "6th St. West ", "Brookside Dr. ", "Hillside Ave. ", "Lake St. ", "13th St. ", "4th Ave. ",
			"5th St. North ", "College St. ", "Dogwood Ln. ", "Mill Rd. ", "7th Ave. ", "8th St. ", "Beech St. ",
			"Division St. ", "Harrison St. ", "Heather Ln. ", "Lakeview Dr. ", "Laurel Ln. ", "New St. ", "Oak Ln. ",
			"Primrose Ln. ", "RailRd. St. ", "Willow St. ", "4th St. North ", "5th St. West ", "6th Ave. ",
			"Berkshire Dr. ", "Buckingham Dr. ", "Circle Dr. ", "Clinton St. ", "George St. ", "Hillcrest Dr. ",
			"Hillside Dr. ", "Laurel St. ", "Park Dr. ", "Penn St. ", "RailRd. Ave. ", "Riverside Dr. ", "Route 32 ",
			"Route 6 ", "Sherwood Dr. ", "Summit St. ", "2nd St. East ", "6th St. North ", "Cedar Ln. ", "Creek Rd. ",
			"Durham Rd. ", "Elm Ave. ", "Fairview Ave. ", "Front St. North ", "Grant St. ", "Hamilton St. ",
			"Highland Dr. ", "Holly Dr. ", "King St. ", "Lafayette Ave. ", "Linden St. ", "Mulberry St. ",
			"Poplar St. ", "Ridge Ave. ", "7th St. East ", "Belmont Ave. ", "Cambridge Ct. ", "Cambridge Dr. ",
			"Clark St. ", "College Ave. ", "Essex Ct. ", "Franklin Ave. ", "Hilltop Rd. ", "James St. ",
			"Magnolia Dr. ", "Myrtle Ave. ", "Route 10 ", "Route 29 ", "Shady Ln. ", "Surrey Ln. ", "Walnut Ave. ",
			"Warren St. ", "Williams St. ", "Wood St. ", "Woodland Ave." };

	/** The cities. */
	public static String[] CITIES = { "Abilene", "Akron", "Albuquerque", "Alexandria", "Allen", "Allentown", "Amarillo",
			"Anaheim", "Anchorage", "Ann Arbor", "Antioch", "Arlington", "Arvada", "Athens", "Atlanta", "Augusta",
			"Aurora", "Aurora", "Austin", "Bakersfield", "Baltimore", "Baton Rouge", "Beaumont", "Bellevue", "Bend",
			"Berkeley", "Billings", "Birmingham", "Boise", "Boston", "Boulder", "Bridgeport", "Broken Arrow",
			"Brownsville", "Buffalo", "Burbank", "Cambridge", "Cape Coral", "Carlsbad", "Carmel", "Carrollton", "Cary",
			"Cedar Rapids", "Centennial", "Chandler", "Charleston", "Charlotte", "Chattanooga", "Chesapeake", "Chicago",
			"Chico", "Chula Vista", "Cincinnati", "Clarksville", "Clearwater", "Cleveland", "Clinton", "Clovis",
			"College Station", "Colorado Springs", "Columbia", "Columbia", "Columbus", "Columbus", "Concord",
			"Coral Springs", "Corona", "Corpus Christi", "Costa Mesa", "Dallas", "Daly City", "Davenport", "Davie",
			"Dayton", "Denton", "Denver", "Des Moines", "Detroit", "Downey", "Durham", "Edinburg", "El Cajon",
			"El Monte", "El Paso", "Elgin", "Elizabeth", "Elk Grove", "Escondido", "Eugene", "Evansville", "Everett",
			"Fairfield", "Fargo", "Fayetteville", "Fontana", "Fort Collins", "Fort Lauderdale", "Fort Wayne",
			"Fort Worth", "Fremont", "Fresno", "Frisco", "Fullerton", "Gainesville", "Garden Grove", "Garland",
			"Gilbert", "Glendale", "Glendale", "Grand Prairie", "Grand Rapids", "Greeley", "Green Bay", "Greensboro",
			"Gresham", "Hampton", "Hartford", "Hayward", "Henderson", "Hialeah", "High Point", "Hillsboro", "Hollywood",
			"Honolulu", "Houston", "Huntington Beach", "Huntsville", "Independence", "Indianapolis", "Inglewood",
			"Irvine", "Irving", "Jackson", "Jacksonville", "Jersey City", "Joliet", "Jurupa Valley", "Kansas City",
			"Kansas City", "Kent", "Killeen", "Knoxville", "Lafayette", "Lakeland", "Lakewood", "Lakewood", "Lancaster",
			"Lansing", "Laredo", "Las Cruces", "Las Vegas", "League City", "Lewisville", "Lexington", "Lincoln",
			"Little Rock", "Long Beach", "Los Angeles", "Louisville", "Lowell", "Lubbock", "Macon", "Madison",
			"Manchester", "McAllen", "McKinney", "Memphis", "Meridian", "Mesa", "Mesquite", "Miami", "Miami Gardens",
			"Midland", "Milwaukee", "Minneapolis", "Miramar", "Mobile", "Modesto", "Montgomery", "Moreno Valley",
			"Murfreesboro", "Murrieta", "Naperville", "Nashville", "New Haven", "New Orleans", "New York", "Newark",
			"Newport News", "Norfolk", "Norman", "North Charleston", "North Las Vegas", "Norwalk", "Oakland",
			"Oceanside", "Odessa", "Oklahoma City", "Olathe", "Omaha", "Ontario", "Orange", "Orlando", "Overland Park",
			"Oxnard", "Palm Bay", "Palmdale", "Pasadena", "Pasadena", "Paterson", "Pearland", "Pembroke Pines",
			"Peoria", "Peoria", "Philadelphia", "Phoenix", "Pittsburgh", "Plano", "Pomona", "Pompano Beach",
			"Port St. Lucie", "Portland", "Providence", "Provo", "Pueblo", "Raleigh", "Rancho Cucamonga", "Reno",
			"Renton", "Rialto", "Richardson", "Richmond", "Richmond", "Riverside", "Rochester", "Rochester", "Rockford",
			"Roseville", "Round Rock", "Sacramento", "Saint Paul", "Salem", "Salinas", "Salt Lake City", "San Angelo",
			"San Antonio", "San Bernardino", "San Diego", "San Francisco", "San Jose", "San Mateo", "Sandy Springs",
			"Santa Ana", "Santa Clara", "Santa Clarita", "Santa Maria", "Santa Rosa", "Savannah", "Scottsdale",
			"Seattle", "Shreveport", "Simi Valley", "Sioux Falls", "South Bend", "Sparks", "Spokane", "Spokane Valley",
			"Springfield", "Springfield", "Springfield", "St. Louis", "St. Petersburg", "Stamford", "Sterling Heights",
			"Stockton", "Sugar Land", "Sunnyvale", "Surprise", "Syracuse", "Tacoma", "Tallahassee", "Tampa", "Temecula",
			"Tempe", "Thornton", "Thousand Oaks", "Toledo", "Topeka", "Torrance", "Tucson", "Tulsa", "Tuscaloosa",
			"Tyler", "Vacaville", "Vallejo", "Vancouver", "Ventura", "Victorville", "Virginia Beach", "Visalia",
			"Vista", "Waco", "Warren", "Washington", "Waterbury", "West Covina", "West Jordan", "West Palm Beach",
			"West Valley City", "Westminster", "Wichita", "Wichita Falls", "Wilmington", "Winston–Salem", "Woodbridge",
			"Worcester", "Yonkers" };

	/** The states. */
	public static String[] STATES = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID",
			"IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
			"NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
			"WI", "WY" };

	/**
	 * Instantiates a new names.
	 */
	public NameUtil() {
		super();
	}

	/**
	 * Gets the random first.
	 *
	 * @param gender the gender
	 * @return the random first
	 */
	public static String getRandomFirst(String gender) {
		Random random = new Random();
		if (gender.equals("M")) {
			String first = MALES[random.nextInt(MALES.length)];
			return first;
		} else {
			String first = FEMALES[random.nextInt(MALES.length)];
			return first;
		}
	}

	/**
	 * Gets the random last.
	 *
	 * @return the random last
	 */
	public static String getRandomLast() {
		Random random = new Random();
		return LASTNAMES[random.nextInt(FEMALES.length)];
	}

	/**
	 * Gets the random address.
	 *
	 * @return the random address
	 */
	public static String getRandomAddress() {
		Random random = new Random();

		return random.nextInt(1000) + " " + STREETS[random.nextInt(STREETS.length)];
	}

	/**
	 * Gets the random city.
	 *
	 * @return the random city
	 */
	public static String getRandomCity() {
		Random random = new Random();
		return CITIES[random.nextInt(CITIES.length)];
	}

	/**
	 * Gets the random state.
	 *
	 * @return the random state
	 */
	public static String getRandomState() {
		Random random = new Random();
		return STATES[random.nextInt(STATES.length)];
	}

}
