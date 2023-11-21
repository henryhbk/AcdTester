package com.ibm.nlp.fhir;

import java.util.Collection;

import org.owasp.encoder.Encode;

import com.ibm.fhir.model.type.Element;
import com.ibm.fhir.model.type.Extension;
import com.ibm.fhir.model.type.Narrative;
import com.ibm.fhir.model.type.Xhtml;
import com.ibm.fhir.model.type.code.NarrativeStatus;

public class PlaintextNarrativeBuilder extends Element.Builder {
	public static final String DIV_OPEN = "<div xmlns=\"http://www.w3.org/1999/xhtml\">";
	public static final String DIV_CLOSE = "</div>";

	Narrative.Builder narrativeBuilder;

	public PlaintextNarrativeBuilder() {
		narrativeBuilder = Narrative.builder();
	}

	/**
	 * Unique id for the element within a resource (for internal references). This
	 * may be any string value that does not contain spaces.
	 *
	 * @param id Unique id for inter-element referencing
	 *
	 * @return A reference to this Builder instance
	 */
	@Override
	public PlaintextNarrativeBuilder id(java.lang.String id) {
		narrativeBuilder.id(id);
		return this;
	}

	/**
	 * May be used to represent additional information that is not part of the basic
	 * definition of the element. To make the use of extensions safe and manageable,
	 * there is a strict set of governance applied to the definition and use of
	 * extensions. Though any implementer can define an extension, there is a set of
	 * requirements that SHALL be met as part of the definition of the extension.
	 *
	 * <p>
	 * Adds new element(s) to the existing list
	 *
	 * @param extension Additional content defined by implementations
	 *
	 * @return A reference to this Builder instance
	 */
	@Override
	public PlaintextNarrativeBuilder extension(Extension... extension) {
		narrativeBuilder.extension(extension);
		return this;
	}

	/**
	 * May be used to represent additional information that is not part of the basic
	 * definition of the element. To make the use of extensions safe and manageable,
	 * there is a strict set of governance applied to the definition and use of
	 * extensions. Though any implementer can define an extension, there is a set of
	 * requirements that SHALL be met as part of the definition of the extension.
	 *
	 * <p>
	 * Replaces the existing list with a new one containing elements from the
	 * Collection
	 *
	 * @param extension Additional content defined by implementations
	 *
	 * @return A reference to this Builder instance
	 */
	@Override
	public PlaintextNarrativeBuilder extension(Collection<Extension> extension) {
		narrativeBuilder.extension(extension);
		return this;
	}

	/**
	 * The status of the narrative - whether it's entirely generated (from just the
	 * defined data or the extensions too), or whether a human authored it and it
	 * may contain additional data.
	 *
	 * <p>
	 * This element is required.
	 *
	 * @param status generated | extensions | additional | empty
	 *
	 * @return A reference to this Builder instance
	 */
	public PlaintextNarrativeBuilder status(NarrativeStatus status) {
		narrativeBuilder.status(status);
		return this;
	}

	/**
	 * The actual narrative content, in plain text.
	 *
	 * <p>
	 * This element is required.
	 *
	 * @param text Plain text content of the narrative
	 *
	 * @return A reference to this Builder instance
	 */
	public PlaintextNarrativeBuilder text(String text) {
		narrativeBuilder.div(Xhtml.of(DIV_OPEN + Encode.forHtmlContent(text) + DIV_CLOSE));
		return this;
	}

	/**
	 * Build the {@link Narrative}
	 *
	 * <p>
	 * Required elements:
	 * <ul>
	 * <li>status</li>
	 * <li>text</li>
	 * </ul>
	 *
	 * @return An immutable object of type {@link Narrative}
	 * @throws IllegalStateException if the current state cannot be built into a
	 *                               valid Narrative per the base specification
	 */
	@Override
	public Narrative build() {
		return narrativeBuilder.build();
	}

	protected PlaintextNarrativeBuilder from(Narrative narrative) {
		narrativeBuilder = narrative.toBuilder();
		return this;
	}
}