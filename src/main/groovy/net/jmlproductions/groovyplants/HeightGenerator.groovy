package net.jmlproductions.groovyplants

class HeightGenerator
{
    private initialHeight
    private slopes
    
    HeightGenerator()
    {
        initialHeight = 0
        slopes = new Slopes()
    }
    
    def generate(int numberOfHeights)
    {
        List<Height> heights = []
        
        def currentHeight = initialHeight
        
        numberOfHeights.times{
            heights << currentHeight
            currentHeight += slopes.next()
        }
        return heights
    }
    
    def startingAtHeight(initialHeight)
    {
        this.initialHeight = initialHeight;
        this
    }
    
    def usingSlopes(slopes) {
        this.slopes = slopes
        this
    }
}
