package net.jmlproductions.groovyplants

import org.junit.*
import static RandomMocker.*

class RandomDeltasTest
{
    @Test
    def void returnsRandomDeltasBetweenNegativeOneAndOne()
    {
        givenRandomDoubles([0.0, 0.75, 0.5])
        {
            random -> givenTestSubjectWithBounds(-1, 1, random)
            {
                underTest -> for (expectedValue in [-1, 0.5, 0]) { assert underTest.next() == expectedValue }
            }
        }
    }

    @Test
    def void returnsRandomDeltasBetweenZeroAndOne()
    {
        givenRandomDoubles([0.0, 0.75, 0.5])
        {
            random -> givenTestSubjectWithBounds(0, 1, random)
            {
                underTest -> for(expectedValue in [0, 0.75, 0.5]) { assert underTest.next() == expectedValue }
            }
        }
    }

    def givenTestSubjectWithBounds(min, max, random, Closure test)
    {
        test(new RandomDeltas(min, max, random))
    }

}
