package net.jmlproductions.groovyplants

import org.junit.*

class HeightGeneratorTest {

    private HeightGenerator heightGenerator = new HeightGenerator();

    @Test
    def void shouldGenerateAFlatSetOf300HeightsAt50()
    {
        List<Height> heights = heightGenerator.startingAtHeight(50).generate(300)

        List<Height> expectedHeights = []
        300.times { expectedHeights += 50 }

        assert heights.equals(expectedHeights)
    }

    @Test
    def void shouldGenerateAFlatSetOf300HeightsAt70()
    {
        List<Height> heights = heightGenerator.startingAtHeight(70).generate(300)

        List<Height> expected = []
        300.times { expected += 70 }
        assert heights.equals(expected)
    }

    @Test
    def void shouldGenerateASetOfTwoHeights()
    {
        List<Height> heights = heightGenerator.startingAtHeight(50).generate(2)

        assert heights.equals([50, 50])
    }

    @Test
    def void shouldGenerateTwoHeightsWithTheSecondHigherByOne()
    {
        List<Height> heights = heightGenerator.startingAtHeight(50).startingWithSlope(1).generate(2)

        assert heights.equals([50, 51])
    }

    @Test
    def void shouldGenerateThreeHeightsWithEachHigherThanTheLastByOne()
    {
        List<Height> heights = heightGenerator.startingWithSlope(1).generate(3)

        assert heights.equals([0, 1, 2])
    }

    @Test
    def void shouldIncreaseAndDecreaseSlopeUsingSetOfSlopeDeltas()
    {
        Deltas slopeDeltas = new PresetDeltas([1.2, -2.4, 0])

        List<Height> heights = heightGenerator.usingDeltas(slopeDeltas).generate(4)

        assert heights == [0, 1, 0, -1]
    }

    @Test
    def void shouldDecreaseSlopeWhenGoingAboveUpperBounds()
    {
        List<Height> heights =
                heightGenerator.startingWithSlope(1).adjustingSlopeBy(-1).whenAbove(0).generate(5)

        assert heights.equals([0, 1, 1, 0, -1])
    }

    @Test
    def void shouldGenerateThreeHeightsAtZero() {
        List<Height> heights = heightGenerator.generate(3)

        assert heights.equals([0, 0, 0])
    }

    class PresetDeltas extends Deltas
    {
        Iterator<Number> deltas = [].listIterator()

        PresetDeltas(List<Number> deltas)
        {
            this.deltas = deltas.listIterator()
        }

        def next()
        {
            if (deltas.hasNext()) return deltas.next()

            return 0
        }
    }
}