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

    def seedAtGroundLevelWithWaterOnLeft = terrainFromStringMatrix([["S", "S", "S", "S"],
                                                                         ["S", "W", "E", "S"],
                                                                         ["D", "D", "D", "D"],
                                                                         ["D", "D", "D", "D"]])

    def terrainWithSprout = terrainFromStringMatrix([["S", "S", "P", "S"],
                                                     ["S", "W", "P", "S"],
                                                     ["D", "D", "R", "D"],
                                                     ["D", "D", "R", "D"]])

    def anotherSeedAtGroundLevelWithWaterOnLeft = terrainFromStringMatrix([["S", "S", "S", "S"],
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

    def randomValueWhichWontRemoveSeed = [0.01]

    def randomValueWhichWillRemoveSeed = [0.00]

    def seedWithWaterOnRight = terrainFromStringMatrix([["S", "S", "S", "S"],
                                                        ["E", "W", "S", "S"],
                                                        ["D", "D", "D", "D"],
                                                        ["D", "D", "D", "D"]])

    def sproutFromWaterOnRight = terrainFromStringMatrix([["P", "S", "S", "S"],
                                                          ["P", "W", "S", "S"],
                                                          ["R", "D", "D", "D"],
                                                          ["R", "D", "D", "D"]])

    def seedWithWaterBelow = terrainFromStringMatrix([["S", "S", "S", "S"],
                                                      ["S", "E", "S", "S"],
                                                      ["D", "W", "D", "D"],
                                                      ["D", "D", "D", "D"]])


    def seedWithWaterAbove = terrainFromStringMatrix([["S", "W", "S", "S"],
                                                      ["S", "E", "S", "S"],
                                                      ["D", "S", "D", "D"],
                                                      ["D", "D", "D", "D"]])

    def sproutWhichOverwritesWater = terrainFromStringMatrix([["S", "P", "S", "S"],
                                                              ["S", "P", "S", "S"],
                                                              ["D", "R", "D", "D"],
                                                              ["D", "R", "D", "D"]])

    def seedWithGroundWaterAbove = terrainFromStringMatrix([["D", "G", "D", "D"],
                                                            ["D", "E", "D", "D"],
                                                            ["D", "D", "D", "D"],
                                                            ["D", "D", "D", "D"]])

    def undergroundSproutWhichOverwritesWater = terrainFromStringMatrix([["D", "P", "D", "D"],
                                                                         ["D", "P", "D", "D"],
                                                                         ["D", "R", "D", "D"],
                                                                         ["D", "R", "D", "D"]])

    def seedWithGroundWaterOnRight = terrainFromStringMatrix([["D", "D", "D", "D"],
                                                              ["D", "D", "E", "G"],
                                                              ["D", "D", "D", "D"],
                                                              ["D", "D", "D", "D"]])

    def undergroundSproutWithGroundWaterOnRight = terrainFromStringMatrix([["D", "D", "P", "D"],
                                                                           ["D", "D", "P", "G"],
                                                                           ["D", "D", "R", "D"],
                                                                           ["D", "D", "R", "D"]])

    def seedWithGroundWaterOnLeft = terrainFromStringMatrix([["D", "D", "D", "D"],
                                                             ["D", "G", "E", "D"],
                                                             ["D", "D", "D", "D"],
                                                             ["D", "D", "D", "D"]])

    def undergroundSproutWithGroundWaterOnLeft = terrainFromStringMatrix([["D", "D", "P", "D"],
                                                                          ["D", "G", "P", "D"],
                                                                          ["D", "D", "R", "D"],
                                                                          ["D", "D", "R", "D"]])

    def seedReplacedByDirt = terrainFromStringMatrix([["S", "S", "S", "S"],
                                                      ["S", "S", "D", "S"],
                                                      ["D", "D", "D", "D"],
                                                      ["D", "D", "D", "D"]])

    def Plants underTest = plantsWithInitialTerrain(skyAndFlatDirt)

    @Test
    def void itHasTheSameTerrainAsWhatItStartsWith()
    {
        assert underTest.terrain == skyAndFlatDirt
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
        testingPlantsGiven(allDirt, randomValuesForGroundWaterCoordinates)
        {
            it.createWaterAt(1, 1)
            assert it.terrain == dirtWithRandomlyPlacedGroundWater
        }
    }

    @Test
    def void itDropsRandomlyGeneratedWaterToGroundLevel()
    {
        testingPlantsGiven(oneRowOfDirtAtBottom, randomValuesForFallingWaterCoordinates)
        {
            it.createWaterAt(1, 0)
            assert it.terrain == dirtWithWaterFallenOnGround
        }
    }

    @Test
    def void itOnlyReplacesDirtAndSkyWithWater()
    {
        testingPlantsGiven(terrainWithNonDirtNonSky, randomValuesForNonSkyNonDirtCoordinates)
        {
            it.createWaterAt(3, 2)
            assert it.terrain == terrainWithNoNonSkyNonDirtReplaced
        }
    }

    @Test
    def void itTurnsASeedWithOneWaterNextToItIntoASprout()
    {
        testingPlantsGiven(seedAtGroundLevelWithWaterOnLeft)
        {
            it.update(2, 1)
            assert it.terrain == terrainWithSprout
        }
    }

    @Test
    def void itTurnsASeedInADifferentLocationWithOneWaterNextToItIntoASprout()
    {
        testingPlantsGiven(anotherSeedAtGroundLevelWithWaterOnLeft)
        {
            it.update(1, 2)
            assert it.terrain == terrainWithAnotherSprout
        }
    }

    @Test
    def void itDoesNothingToASeedWithNoWaterNextToItNinetyNineHundredthsOfTheTime()
    {
        testingPlantsGiven(aSeedWithNoWaterNextToIt, randomValueWhichWontRemoveSeed)
        {
            it.update(2, 1)
            assert it.terrain == aSeedWithNoWaterNextToIt
        }
    }

    @Test
    def void itTurnsASeedIntoASproutWhenWaterIsToTheRightOfTheSeed()
    {
        testingPlantsGiven(seedWithWaterOnRight, randomValueWhichWontRemoveSeed)
        {
            it.update(0, 1)
            assert it.terrain == sproutFromWaterOnRight
        }
    }

    @Test
    def void itTurnsASeedIntoASproutWhenWaterIsBelowTheSeed()
    {
        testingPlantsGiven(seedWithWaterBelow, randomValueWhichWontRemoveSeed)
        {
            it.update(1, 1)
            assert it.terrain == sproutWhichOverwritesWater
        }
    }

    @Test
    def void itTurnsASeedIntoASproutWhenWaterIsAboveTheSeed()
    {
        testingPlantsGiven(seedWithWaterAbove, randomValueWhichWontRemoveSeed)
        {
            it.update(1, 1)
            assert it.terrain == sproutWhichOverwritesWater
        }
    }

    @Test
    def void itTurnsASeedIntoASproutWhenGroundWaterIsAboveTheSeed()
    {
        testingPlantsGiven(seedWithGroundWaterAbove, randomValueWhichWontRemoveSeed)
        {
            it.update(1, 1)
            assert it.terrain == undergroundSproutWhichOverwritesWater
        }
    }

    @Test
    def void itTurnsASeedIntoASproutWhenGroundWaterIsToTheRightOfTheSeed()
    {
        testingPlantsGiven(seedWithGroundWaterOnRight, randomValueWhichWontRemoveSeed)
        {
            it.update(2, 1)
            assert it.terrain == undergroundSproutWithGroundWaterOnRight
        }
    }

    @Test
    def void itTurnsASeedIntoASproutWhenGroundWaterIsToTheLeftOfTheSeed()
    {
        testingPlantsGiven(seedWithGroundWaterOnLeft, randomValueWhichWontRemoveSeed)
        {
            it.update(2, 1)
            assert it.terrain == undergroundSproutWithGroundWaterOnLeft
        }
    }

    @Test
    def void itTurnsASeedIntoDirtWhenThereIsNoWaterAroundTheSeedOneHundredthOfTheTime()
    {
        testingPlantsGiven(aSeedWithNoWaterNextToIt, randomValueWhichWillRemoveSeed)
        {
            it.update(2, 1)
            assert it.terrain == seedReplacedByDirt
        }
    }

    def void testingPlantsGiven(terrain, randomValues = [0.0], test)
    {
        givenRandomIntsFromDoubles(randomValues)
        {
            random ->
            def underTest = new Plants([generate: { terrain.collectNested{ it } }], random)
            test(underTest)
        }
    }

    def plantsWithInitialTerrain(initialTerrain)
    {
        new Plants([generate: { initialTerrain.collectNested{ it } }], new Random())
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
