package net.jmlproductions.groovyplants

import org.junit.*

class RandomDeltasTest
{
    @Before
    def void setup()
    {
        def iterator = [0.0, 0.75, 0.5].listIterator()
        Random.metaClass.nextDouble = { iterator.next() }
    }

    @Test
    def void returnsRandomDeltasBetweenNegativeOneAndOne()
    {
        def deltas = []
        3.times { deltas << (new RandomDeltas(-1, 1) as Deltas).next() }

        assert deltas == [-1, 0.5, 0]
    }

    @Test
    def void returnsRandomDeltasBetweenZeroAndOne()
    {
        def deltas = []
        3.times { deltas << (new RandomDeltas(0, 1) as Deltas).next() }

        assert deltas == [0, 0.75, 0.5]
    }
}
