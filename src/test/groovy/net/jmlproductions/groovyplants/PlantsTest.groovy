package net.jmlproductions.groovyplants

import org.junit.*
import static net.jmlproductions.groovyplants.TerrainType.*
import static net.jmlproductions.groovyplants.RandomMocker.*

class PlantsTest
{
    def stringsToTerrainTypes = ["D": Dirt, "W": Water, "E": Seed, "S": Sky, "X": DeadPlant,
                                 "G": GroundWater, "R": Root, "P": Plant, "F": Fruit]

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

    def terrainWithNonDirtNonSky = terrainFromStringMatrix([["S", "S", "S", "S", "S", "S", "S", "S"],
                                                            ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                            ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                            ["D", "D", "D", "P", "F", "D", "D", "D"],
                                                            ["D", "D", "D", "E", "X", "D", "D", "D"],
                                                            ["D", "D", "D", "R", "D", "D", "D", "D"],
                                                            ["D", "D", "D", "D", "D", "D", "D", "D"],
                                                            ["D", "D", "D", "D", "D", "D", "D", "D"],
                                                            ["D", "D", "D", "D", "D", "D", "D", "D"]])

    def terrainWithNoNonSkyNonDirtReplaced = terrainFromStringMatrix([["S", "S", "S", "S", "S", "S", "S", "S"],
                                                                      ["S", "S", "S", "S", "S", "S", "S", "S"],
                                                                      ["S", "S", "S", "W", "S", "S", "S", "S"],
                                                                      ["D", "D", "D", "P", "F", "D", "D", "D"],
                                                                      ["D", "D", "D", "E", "X", "D", "D", "D"],
                                                                      ["D", "D", "D", "R", "D", "D", "D", "D"],
                                                                      ["D", "D", "D", "D", "D", "D", "D", "D"],
                                                                      ["D", "D", "D", "D", "D", "D", "D", "D"],
                                                                      ["D", "D", "D", "D", "D", "D", "D", "D"]])

    def randomValuesForNonSkyNonDirtCoordinates = [0.00, 0.00,
                                                   0.00, 0.00,
                                                   0.00, 0.00,
                                                   0.00, 0.00,
                                                   0.00, 0.25,
                                                   0.00, 0.50,
                                                   0.00, 0.75,
                                                   0.25, 0.25,
                                                   0.25, 0.50,
                                                   0.00, 0.00,
                                                   0.00, 0.00,
                                                   0.00, 0.00]

    def seedAtGroundLevelWithNeighboringWater = terrainFromStringMatrix([["S", "S", "S", "S"],
                                                                         ["S", "W", "E", "S"],
                                                                         ["D", "D", "D", "D"],
                                                                         ["D", "D", "D", "D"]])

    def terrainWithSprout = terrainFromStringMatrix([["S", "S", "P", "S"],
                                                     ["S", "W", "P", "S"],
                                                     ["D", "D", "R", "D"],
                                                     ["D", "D", "R", "D"]])

    def anotherSeedAtGroundLevelWithNeighboringWater = terrainFromStringMatrix([["S", "S", "S", "S"],
                                                                                ["S", "S", "S", "S"],
                                                                                ["W", "E", "S", "S"],
                                                                                ["D", "D", "D", "D"],
                                                                                ["D", "D", "D", "D"]])

    def terrainWithAnotherSprout = terrainFromStringMatrix([["S", "S", "S", "S"],
                                                            ["S", "P", "S", "S"],
                                                            ["W", "P", "S", "S"],
                                                            ["D", "R", "D", "D"],
                                                            ["D", "R", "D", "D"]])

    def aSeedWithNoWaterNextToIt = terrainFromStringMatrix([["S", "S", "S", "S"],
                                                            ["S", "S", "E", "S"],
                                                            ["D", "D", "D", "D"],
                                                            ["D", "D", "D", "D"]])

    def randomValueWhichWontRemoveSeed = [0.02]

    def Plants underTest = plantsWithInitialTerrain(skyAndFlatDirt)

    @Test
    def void itHasTheSameTerrainAsWhatItStartsWith()
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
        testingPlantsWith(allDirt, randomValuesForGroundWaterCoordinates)
        {
            it.createWaterAt(1, 1)
            assert it.terrain == dirtWithRandomlyPlacedGroundWater
        }
    }

    @Test
    def void itDropsRandomlyGeneratedWaterToGroundLevel()
    {
        testingPlantsWith(oneRowOfDirtAtBottom, randomValuesForFallingWaterCoordinates)
        {
            it.createWaterAt(1, 0)
            assert it.terrain == dirtWithWaterFallenOnGround
        }
    }

    @Test
    def void itOnlyReplacesDirtAndSkyWithWater()
    {
        testingPlantsWith(terrainWithNonDirtNonSky, randomValuesForNonSkyNonDirtCoordinates)
        {
            it.createWaterAt(3, 2)
            assert it.terrain == terrainWithNoNonSkyNonDirtReplaced
        }
    }

    @Test
    def void itTurnsASeedWithOneWaterNextToItIntoASprout()
    {
        testingPlantsWith(seedAtGroundLevelWithNeighboringWater)
        {
            it.update(2, 1)
            assert it.terrain == terrainWithSprout
        }
    }

    @Test
    def void itTurnsASeedInADifferentLocationWithOneWaterNextToItIntoASprout()
    {
        testingPlantsWith(anotherSeedAtGroundLevelWithNeighboringWater)
        {
            it.update(1, 2)
            assert it.terrain == terrainWithAnotherSprout
        }
    }

    @Test
    def void itDoesNothingToASeedWithNoWaterNextToIt()
    {
        testingPlantsWith(aSeedWithNoWaterNextToIt, randomValueWhichWontRemoveSeed)
        {
            it.update(2, 1)
            assert it.terrain == aSeedWithNoWaterNextToIt
        }
    }
    
    def void testingPlantsWith(terrain, randomValues = [0.0], test)
    {
        givenRandomIntsFromDoubles(randomValues)
        {
            random ->
            def underTest = new Plants([generate: { terrain }], random)
            test(underTest)
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
