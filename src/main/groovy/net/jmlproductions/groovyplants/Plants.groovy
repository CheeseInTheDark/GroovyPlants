package net.jmlproductions.groovyplants

import static TerrainType.*

class Plants
{
    private terrain
    private random

    def private down =  [x: 0, y: 1]
    def private right = [x: 1, y: 0]
    def private left =  [x: -1, y: 0]
    def private up =    [x: 0, y: -1]

    def private adjacent = [up, right, down, left]
    def private leftRightAndAbove = adjacent - down

    Plants(terrainGenerator, random)
    {
        this.terrain = terrainGenerator.generate() as List
        this.random = random
    }

    def createSeedAt(x, y)
    {
        terrain[x][heightOfFirstNonSkyUnder(x, y)] = Seed
    }

    def createWaterAt(originX, originY)
    {
        12.times
        {
            def x = originX + random.nextInt(5)
            def y = originY + random.nextInt(5)

            if(terrain[x][y].is(Dirt))
            {
                terrain[x][y] = GroundWater
            }
            else if (terrain[x][y].is(Sky))
            {
                y = heightOfFirstNonSkyUnder(x, y)
                terrain[x][y] = Water
            }
        }
    }

    def update(x, y)
    {
        def pointToUpdate = coordinatePair(x, y)
        def neighborsLeftRightAndAbove = setOfTerrainsAt(pointToUpdate, leftRightAndAbove)
        def neighborsAdjacent = setOfTerrainsAt(pointToUpdate, adjacent)

        if (anyTerrainAtCoordinatesMatches(neighborsLeftRightAndAbove) { it == GroundWater } ||
            anyTerrainAtCoordinatesMatches(neighborsAdjacent) { it == Water })
        {
            terrain[x][y - 1] = Plant
            terrain[x][y] = Plant
            terrain[x][y + 1] = Root
            terrain[x][y + 2] = Root
        }
        else if (random.nextInt(100) == 0)
        {
            terrain[x][y] = Dirt
        }
    }

    def private coordinatePair(x, y)
    {
        [x: x, y: y]
    }

    def private setOfTerrainsAt(origin, List offsets)
    {
        offsets.collect { [x: origin.x + it.x, y: origin.y + it.y] }
    }

    def private anyTerrainAtCoordinatesMatches(coordinates, condition)
    {
        coordinates.any { condition(terrain[it.x][it.y]) }
    }

    def private heightOfFirstNonSkyUnder(x, y)
    {
        if (pointUnder(x, y).is(Sky)) { firstPointBelow(x, y) { it != Sky } }
        else { y }
    }

    def private pointUnder(x, y)
    {
        terrain[x][y+1]
    }

    def private firstPointBelow(x, y, condition)
    {
        terrain[x].findIndexOf(y, condition) - 1
    }

}
