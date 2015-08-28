package net.jmlproductions.groovyplants;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.contains;

import java.awt.image.BufferedImage
import org.junit.*;

class HeightGeneratorTest {
    
    @Test
    def void shouldDrawAFlatLandscape() {
        VaryingSlope slope = {nextSlope: 0} as VaryingSlope;
        HeightGenerator underTest = new HeightGenerator(slope);
        
        List<Height> heights = underTest.generate(300).startingAtHeight(50);
        
        List<Height> expected = []
         300.times{ expected += 50 };
        
        assert heights.containsAll(expected);
    }
}