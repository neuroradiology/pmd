/*
 * User: tom
 * Date: Jul 31, 2002
 * Time: 12:25:01 PM
 */
package test.net.sourceforge.pmd.cpd;

import junit.framework.TestCase;
import net.sourceforge.pmd.cpd.*;

import java.util.Iterator;

public class OccurrencesTest  extends TestCase {
    public OccurrencesTest(String name) {
        super(name);
    }

    public void testBasic() {
        Occurrences occs = new Occurrences();
        assertTrue(!occs.contains(new Token('h', 0, "foo")));
        assertTrue(!occs.getTiles().hasNext());
        assertTrue(occs.isEmpty());
        assertEquals(0, occs.size());

        occs.addInitial(createTokenSetFor("foo"));
        assertEquals(4, occs.size());
        assertTrue(occs.contains(new Token('h', 0, "foo")));
        Iterator i = occs.getOccurrences(new Tile(new Token('h', 0, "foo")));
        assertTrue(i.hasNext());
        assertTrue(occs.getTiles().hasNext());
        int count = 0;
        for (Iterator foo = occs.getTiles(); foo.hasNext();) {
            foo.next();
            count++;
        }
        assertEquals(4, count);
    }

    public void testInitialFrequencyCount() {
        Occurrences occs = new Occurrences();
        occs.addInitial(createTokenSetFor("foo"));
        Iterator i = occs.getOccurrences(new Tile(new Token('h', 0, "foo")));
        Occurrence occ = (Occurrence)i.next();
        assertEquals("foo", occ.getTokenSetID());
        assertEquals(0,occ.getIndex());
    }

    public void testContains() {
        Occurrences occs = new Occurrences();
        occs.addInitial(createTokenSetFor("foo"));
        assertTrue(occs.contains(new Token('h', 0, "foo")));
    }

    public void testDeleteSolo() {
        Occurrences occs = new Occurrences();
        occs.addInitial(createTokenSetFor("foo"));
        occs.deleteSoloTiles();
        assertEquals(1, occs.size());
        assertTrue(!occs.contains(new Token('h', 0, "foo")));
        assertTrue(occs.contains(new Token('l', 2, "foo")));
    }

    public void testConsolidate() {
        Occurrences occs = new Occurrences();
        TokenSet ts = createTokenSetFor("foo");

    }

    private TokenSet createTokenSetFor(String id) {
        TokenSet ts = new TokenSet(id);
        ts.add(new Token('h', 0, id));
        ts.add(new Token('e', 1, id));
        ts.add(new Token('l', 2, id));
        ts.add(new Token('l', 3, id));
        ts.add(new Token('o', 4, id));
        return ts;
    }
}
