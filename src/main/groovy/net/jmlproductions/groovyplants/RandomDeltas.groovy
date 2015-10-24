package net.jmlproductions.groovyplants

class RandomDeltas extends Deltas
{
    def range
    def lowerBound
    def random

    RandomDeltas(lowerBound, upperBound, random)
    {
        this.random = random
        this.range = upperBound - lowerBound
        this.lowerBound = lowerBound
    }

    def next()
    {
        random.nextDouble() * range + lowerBound
    }
}
