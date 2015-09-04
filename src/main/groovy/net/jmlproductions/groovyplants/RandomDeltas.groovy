package net.jmlproductions.groovyplants

class RandomDeltas extends Deltas
{
    def range
    def centeringValue
    def Random random

    RandomDeltas(lowerBound, upperBound)
    {
        this.random = new Random()
        this.range = upperBound - lowerBound
        this.centeringValue = range/2
    }

    def next()
    {
        random.nextDouble() * range - centeringValue
    }
}
