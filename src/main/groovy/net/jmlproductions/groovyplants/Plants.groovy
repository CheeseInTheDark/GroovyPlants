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
        if (terrainUnder(x, y).is(Sky))
        {
            y = terrain[x].findIndexOf(y) {it != Sky} - 1
        }

        terrain[x][y] = Seed
    }

    def createWaterAt(x, y)
    {
        12.times
        {
            def candidateX = x + random.nextInt(5)
            def candidateY = y + random.nextInt(5)

            if(terrain[candidateX][candidateY].is(Dirt))
            {
                terrain[candidateX][candidateY] = GroundWater
            }
            else
            {
                if (terrainUnder(candidateX, candidateY).is(Sky))
                {
                    candidateY = terrain[candidateX].findIndexOf(candidateY) {it != Sky} - 1
                }

                terrain[candidateX][candidateY] = Water
            }
        }
    }

    def private terrainUnder(x, y)
    {
        terrain[x][y+1]
    }
}
