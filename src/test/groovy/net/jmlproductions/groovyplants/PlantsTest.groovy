package net.jmlproductions.groovyplants

import org.junit.*
import static net.jmlproductions.groovyplants.TerrainType.*
import static net.jmlproductions.groovyplants.RandomMocker.*

class PlantsTest
{
    def stringsToTerrainTypes = ["D": Dirt, "W": Water, "E": Seed, "S": Sky, "G": GroundWater]

    def skyAndFlatDirt = terrainFromStringMatrix([["S", "S", "S", "S", "S", "S", "S", "S", "S"],
                                                  ["S", "S", "S", "S", "S", "S", "S", "S", "S"],
                                                  ["S", "S", "S", "S", "S", "S", "S", "S", "S"],
                                                  ["S", "S", "S", "S", "S", "S", "S", "S", "S"],
                                                  ["D", "D", "D", "D", "D", "D", "D", "D", "D"],
                                                  ["D", "D", "D", "D", "D", "D", "D", "D", "D"],
                                                  ["D", "D", "D", "D", "D", "D", "D", "D", "D"]])

    def allSky = terrainFromStringMatrix([["S", "S", "S", "S", "S", "S", "S", "S"],
                                          ["S", "S", "S", "S", "S", "S", "S", "S"],
                                          ["S", "S", "S", "S", "S", "S", "S", "S"],
                                          ["S", "S", "S", "S", "S", "S", "S", "S"],
                                          ["S", "S", "S", "S", "S", "S", "S", "S"],
                                          ["S", "S", "S", "S", "S", "S", "S", "S"],
                                          ["S", "S", "S", "S", "S", "S", "S", "S"],
                                          ["S", "S", "S", "S", "S", "S", "S", "S"],
                                          ["S", "S", "S", "S", "S", "S", "S", "S"]])

    def allDirt = terrainFromStringMatrix([["D", "D", "D", "D", "D", "D", "D", "D"],
                                           ["D", "D", "D", "D", "D", "D", "D", "D"],
                                           ["D", "D", "D", "D", "D", "D", "D", "D"],
                                           ["D", "D", "D", "D", "D", "D", "D", "D"],
                                           ["D", "D", "D", "D", "D", "D", "D", "D"],
                                           ["D", "D", "D", "D", "D", "D", "D", "D"],
                                           ["D", "D", "D", "D", "D", "D", "D", "D"],
                                           ["D", "D", "D", "D", "D", "D", "D", "D"],
                                           ["D", "D", "D", "D", "D", "D", "D", "D"]])

    def dirtWithRandomlyPlacedGroundWater = terrainFromStringMatrix([["D", "D", "D", "D", "D", "D", "D", "D"],
                                                                     ["D", "G", "G", "G", "G", "G", "D", "D"],
                                                                     ["D", "G", "D", "D", "D", "D", "D", "D"],
                                                                     ["D", "G", "D", "G", "D", "D", "D", "D"],
                                                                     ["D", "G", "D", "D", "D", "D", "D", "D"],
                                                                     ["D", "D", "G", "G", "D", "G", "D", "D"],
                                                                     ["D", "D", "D", "D", "D", "D", "D", "D"],
                                                                     ["D", "D", "D", "D", "D", "D", "D", "D"],
                                                                     ["D", "D", "D", "D", "D", "D", "D", "D"]])

    def randomValuesForGroundWaterCoordinates = [0.00, 0.00,
                                                 0.25, 0.00,
                                                 0.50, 0.00,
                                                 0.75, 0.00,
                                                 1.00, 0.00,
                                                 0.00, 0.25,
                                                 0.00, 0.50,
                                                 0.00, 0.75,
                                                 0.50, 0.50,
                                                 0.25, 1.00,
                                                 0.50, 1.00,
                                                 1.00, 1.00]

    def oneRowOfDirtAtBottom = terrainFromStringMatrix([["S", "S", "S", "S", "S", "S", "S", "S"],
                                                        ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                        ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                        ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                        ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                        ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                        ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                        ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                        ["D", "D", "D", "D", "D", "D", "D", "D"]])
    
    def dirtWithWaterFallenOnGround = terrainFromStringMatrix([["S", "S", "S", "S", "S", "S", "S", "S"],
                                                               ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                               ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                               ["S", "S", "W", "S", "S", "S", "S", "S"],
                                                               ["S", "S", "W", "S", "S", "S", "S", "S"],
                                                               ["S", "S", "W", "W", "S", "S", "S", "S"],
                                                               ["S", "W", "W", "W", "S", "S", "S", "S"],
                                                               ["S", "W", "W", "W", "W", "W", "S", "S"],
                                                               ["D", "D", "D", "D", "D", "D", "D", "D"]])
    
    def randomValuesForFallingWaterCoordinates = [0.00, 0.00,
                                                  0.25, 0.00,
                                                  0.75, 0.00,
                                                  0.50, 0.00,
                                                  1.00, 0.00,
                                                  0.00, 0.00,
                                                  0.25, 0.00,
                                                  0.25, 0.00,
                                                  0.50, 0.00,
                                                  0.25, 0.00,
                                                  0.50, 0.00,
                                                  0.25, 0.00]

    def Plants underTest = plantsWithInitialTerrain(skyAndFlatDirt)

    @Test
    def void itReturnsTheTerrainItStartsWith()
    {
        assert underTest.terrain.is(skyAndFlatDirt)
    }

    @Test
    def void itCreatesASeedAtAGivenLocation()
    {
        underTest.createSeedAt(2, 5)

        assert underTest.terrain[2][5] == Seed
    }

    @Test
    def void itDoesNotCreateSeedsInTheSky()
    {
        underTest.createSeedAt(4, 0)

        assert underTest.terrain[4][3] == Seed
    }

    @Test
    def void itCreatesTwelveRandomSpotsOfWaterInAFiveByFiveArea()
    {
        givenRandomIntsFromDoubles(randomValuesForGroundWaterCoordinates)
        {
            random ->
            def underTest = new Plants([generate: {allDirt}], random)

            underTest.createWaterAt(1, 1)

            assert underTest.terrain == dirtWithRandomlyPlacedGroundWater
        }
    }

    @Test
    def void itDropsRandomlyGeneratedWaterToGroundLevel()
    {
        givenRandomIntsFromDoubles(randomValuesForFallingWaterCoordinates)
        {
            random ->
            def underTest = new Plants([generate: {oneRowOfDirtAtBottom}], random)

            underTest.createWaterAt(1, 0)

            assert underTest.terrain == dirtWithWaterFallenOnGround
        }
    }

    def plantsWithInitialTerrain(initialTerrain)
    {
        new Plants([generate: {initialTerrain}], new Random())
    }

    def terrainFromStringMatrix(terrainStrings)
    {
        terrainStrings.collect {
            row -> row.collect {
                terrainTypeString -> stringsToTerrainTypes[terrainTypeString]
            }
        }.transpose()
    }
}
