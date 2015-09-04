package net.jmlproductions.groovyplants

import org.junit.*

class RandomDeltasTest
{
	def originalNextDouble
		
    @Before
    def void setup()
    {
		originalNextDouble = Random.metaClass.getMetaMethod("nextDouble", [] as Class[])
        def iterator = [0.0, 0.75, 0.5].listIterator()
        Random.metaClass.nextDouble = { iterator.next() }
    }

	@After
	def void teardown()
	{
		Random.metaClass.nextDouble = { originalNextDouble.invoke(delegate) }
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
