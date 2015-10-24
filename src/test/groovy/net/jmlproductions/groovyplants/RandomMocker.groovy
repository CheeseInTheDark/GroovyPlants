package net.jmlproductions.groovyplants

import static java.lang.Math.round

class RandomMocker
{
    def static givenRandomIntsFromDoubles(List<Double> doubles, Closure test)
    {
        def iterator = doubles.listIterator()
        def random = [nextInt: { int maxValue -> (int) round(iterator.next() * (maxValue - 1)) }]
        test(random)
    }

    def static givenRandomDoubles(List<Double> doubles, Closure test)
    {
        def iterator = doubles.listIterator()
        def random = [nextDouble: { iterator.next() }] as Random
        test(random)
    }
}
