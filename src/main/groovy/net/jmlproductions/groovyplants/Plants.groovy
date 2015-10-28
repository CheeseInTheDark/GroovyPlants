package net.jmlproductions.groovyplants

import static TerrainType.*

class Plants
{
    private terrain
    private random

    Plants(terrainGenerator, random)
    {
        this.terrain = terrainGenerator.generate()
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
        terrain[x][y-1] = Plant
        terrain[x][y] = Plant
        terrain[x][y+1] = Root
        terrain[x][y+2] = Root
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
