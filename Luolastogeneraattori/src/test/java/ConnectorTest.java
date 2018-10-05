
import generaattori.Connector;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;



public class ConnectorTest {
    Connector connector;
    
    @Before
    public void setUp() {
        connector = new Connector(6, 24, 5, 7);
    }
    
    @Test
    public void getXWorks() {
        Assert.assertEquals(6, connector.getX());
    }
    
    @Test
    public void getYWorks() {
        Assert.assertEquals(24, connector.getY());
    }
    
    @Test
    public void getRegion1Works() {
        Assert.assertEquals(5, connector.getRegion1());
    }
    
    @Test
    public void getRegion2Works() {
        Assert.assertEquals(7, connector.getRegion2());
    }
}
