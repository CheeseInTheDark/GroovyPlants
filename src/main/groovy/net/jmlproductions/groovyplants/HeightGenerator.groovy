package net.jmlproductions.groovyplants

class HeightGenerator
{
    private initialHeight
    private slope
    private Deltas deltas
    private upperBoundSlopeCorrection
    private upperBoundHeight
    private lowerBoundSlopeCorrection
    private lowerBoundHeight

    HeightGenerator()
    {
        initialHeight = 0.0
        upperBoundSlopeCorrection = 0.0
        upperBoundHeight = 0.0
        lowerBoundSlopeCorrection = 0.0
        lowerBoundHeight = 0.0
        slope = 0.0
        deltas = new Deltas()
    }
    
    def generate(int numberOfHeights)
    {
        List<Height> heights = []
        
        Number currentHeight = initialHeight

        (numberOfHeights).times{
            heights << currentHeight.intValue()

            slope += deltas.next()
            currentHeight += slope

            if (currentHeight > upperBoundHeight) { slope += upperBoundSlopeCorrection }
            if (currentHeight < lowerBoundHeight) { slope += lowerBoundSlopeCorrection }
        }

        return heights
    }

    def startingAtHeight(initialHeight)
    {
        this.initialHeight = initialHeight
        return this
    }

    def startingWithSlope(slope)
    {
        this.slope = slope
        return this
    }

    def usingDeltas(deltas)
    {
        this.deltas = deltas;
        return this
    }

    def adjustingSlopeBy(adjustmentAmount)
    {
        [whenAbove: {height ->
            upperBoundSlopeCorrection = adjustmentAmount
            upperBoundHeight = height
            this},
        whenBelow: {height ->
            lowerBoundSlopeCorrection = adjustmentAmount
            lowerBoundHeight = height
            this}]
    }

}
