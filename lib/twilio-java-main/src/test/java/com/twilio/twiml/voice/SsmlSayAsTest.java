/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.twiml.voice;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for {@link SsmlSayAs}
 */
public class SsmlSayAsTest {
    @Test
    public void testElementWithParams() {
        SsmlSayAs elem = new SsmlSayAs.Builder("words")
            .interpretAs(SsmlSayAs.InterpretAs.CHARACTERS)
            .format(SsmlSayAs.Format.MDY)
            .build();

        Assert.assertEquals(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<say-as format=\"mdy\" interpret-as=\"characters\">words</say-as>",
            elem.toXml()
        );
    }

    @Test
    public void testXmlAttributesDeserialization() {
        final SsmlSayAs elem = new SsmlSayAs.Builder("words")
            .interpretAs(SsmlSayAs.InterpretAs.CHARACTERS)
            .format(SsmlSayAs.Format.MDY)
            .build();

        Assert.assertEquals(
            SsmlSayAs.Builder.fromXml("<say-as format=\"mdy\" interpret-as=\"characters\">words</say-as>").build().toXml(),
            elem.toXml()
        );
    }
}