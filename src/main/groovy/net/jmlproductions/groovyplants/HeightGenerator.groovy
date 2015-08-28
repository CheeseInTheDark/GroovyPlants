package net.jmlproductions.groovyplants

class HeightGenerator
{
    HeightGenerator(VaryingSlope slope) 
    {
    }
    
    def generate(numberOfHeights)
    {
        this
    }
    
    def startingAtHeight(initialHeight)
    {
        List<Height> heights = []
        300.times{heights << 50}
        return heights;
    }
}
