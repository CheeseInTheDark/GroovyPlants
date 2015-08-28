package net.jmlproductions.groovyplants

import static org.mockito.Mockito.*
import org.junit.*

class HeightGeneratorTest {
    
    private HeightGenerator underTest = new HeightGenerator();
    
    @Test
    def void shouldGenerateAFlatSetOf300HeightsAt50() {
        List<Height> heights = underTest.startingAtHeight(50).generate(300)
        
        List<Height> expectedHeights = []
         300.times{ expectedHeights += 50 }
        
        assert heights.equals(expectedHeights)
    }
    
    @Test
    def void shouldGenerateAFlatSetOf300HeightsAt70() {
        List<Height> heights = underTest.startingAtHeight(70).generate(300)
        
        List<Height> expected = []
         300.times{expected += 70}
         assert heights.equals(expected)
    }
    
    @Test
    def void shouldGenerateASetOfTwoHeights() {
        List<Height> heights = underTest.startingAtHeight(50).generate(2)
        
        assert heights.equals([50, 50])
    }
    
    @Test
    def void shouldGenerateTwoHeightsWithTheSecondHigherByOne() {
        Slopes slope = [next: {1}] as Slopes
        
        List<Height> heights = underTest.usingSlopes(slope).startingAtHeight(50).generate(2)
        
        assert heights.equals([50, 51])
    }
    
    @Test
    def void shouldGenerateTenHeightsAt0() {
        List<Height> heights = underTest.generate(10)
        
        assert heights.equals([0, 0, 0, 0, 0, 0, 0, 0, 0, 0])
    }
}