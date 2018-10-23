package tietorakenteetTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tietorakenteet.Unionfind;

/**
 *
 * @author ogrousu
 */
public class UnionFindTest {
    Unionfind uf;
    
    @Before
    public void setUp() {
        uf = new Unionfind(10);
    }
    
    @Test
    public void idWorksOnAnUnattachedVertex() {
        Assert.assertEquals(2, this.uf.id(2));
    }
    
    @Test
    public void idWorksWhenMultipleVerticesAreUnified() {
        this.uf.unify(4, 2);
        Assert.assertEquals(4, this.uf.id(2));

        this.uf.unify(2, 7);
        Assert.assertEquals(4, this.uf.id(7));
        
    }
    
    @Test
    public void unifiedReturnsFalseWhenIdIsDifferent() {
        Assert.assertFalse(this.uf.unified(4, 1));
    }
    
    @Test
    public void unifiedWorksWhenIdIsTheSame() {
        this.uf.unify(4, 1);
        Assert.assertTrue(this.uf.unified(4, 1));
    }
    
    @Test
    public void unifyingWorks() {
        Assert.assertFalse(this.uf.unified(4, 6));
        
        this.uf.unify(4, 6);
        
        Assert.assertTrue(this.uf.unified(4, 6));
        
        this.uf.unify(7, 6);
        Assert.assertTrue(this.uf.unified(7, 4));
    }
    
}
