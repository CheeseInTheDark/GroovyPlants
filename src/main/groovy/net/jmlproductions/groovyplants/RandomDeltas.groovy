package net.jmlproductions.groovyplants

class RandomDeltas extends Deltas
{
    def range
    def lowerBound
    def Random random

    RandomDeltas(lowerBound, upperBound)
    {
        this.random = new Random()
        this.range = upperBound - lowerBound
        this.lowerBound = -lowerBound
    }

    def next()
    {
        random.nextDouble() * range - lowerBound
    }
}
