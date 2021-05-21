# Poke-Api-Web-Flux

Poke-Api-Web-Flux is a RESTful API that makes concurrent requests to pokeapi.co API using web flux and stores data in memory with H2. 

## End Points

### Get all API end points
`GET /api/v1//help`

### Response

    {
        "name": "Elias Lopez Cabrera",
        "email": "eliaslcabrera@gmail.com",
        "phone": "6242241951",
        "gitHub": "EliasLC",
        "_links": {
            "self": {
                "href": "http://localhost:8080/api/v1/help"
            },
            "pokemonByNumber": {
                "href": "http://localhost:8080/api/v1/pokeapi/1"
            },
            "pokemonByName": {
                "href": "http://localhost:8080/api/v1/pokeapi/bulbasaur"
            },
            "pokemonsByGeneration": {
                "href": "http://localhost:8080/api/v1/pokeapi/generation/1"
            },
            "pokemonsByRange": {
                "href": "http://localhost:8080/api/v1/pokeapi?start=1&end=5"
            },
            "savePokemon": {
                "href": "http://localhost:8080/api/v1/pokemons"
            },
            "findByNickName": {
                "href": "http://localhost:8080/api/v1/pokemons/nickname"
            },
            "pokemonBySpecies": {
                "href": "http://localhost:8080/api/v1/pokemons?species=species&page=0&size=10"
            }
        }
    }

### Get Pokemon info by number in the pokedex
`GET /api/v1/pokeapi/1`

### Response 

    {
        "id": 1,
        "name": "bulbasaur",
        "types": [
            "grass",
            "poison"
        ],
        "_links": {
            "self": {
                "href": "http://localhost:8080/api/v1/pokeapi/1"
            }
        }
    }

### Get Pokemon info by species
`GET /api/v1/pokeapi/bulbasaur`

### Response
    {
        "id": 1,
        "name": "bulbasaur",
        "types": [
            "grass",
            "poison"
        ],
        "_links": {
            "self": {
                "href": "http://localhost:8080/api/v1/pokeapi/bulbasaur"
            }
        }
    }

### Get pokemos by generation number
`GET /api/v1/pokeapi/generation/1`
  
    [
        {
        "id": 1,
        "name": "bulbasaur",
        "types": [
            "grass",
            "poison"
        ]
        },
        {
            "id": 2,
            "name": "ivysaur",
            "types": [
                "grass",
                "poison"
            ]
        },
        {
            "id": 3,
            "name": "venusaur",
            "types": [
                "grass",
                "poison"
            ]
        },
        {
            "id": 4,
            "name": "charmander",
            "types": [
                "fire"
            ]
        },
        ...
    ]

### Get pokemons by id range
`GET /api/v1/pokeapi?start=1&end=3`

    {
        "id": 1,
        "name": "bulbasaur",
        "types": [
            "grass",
            "poison"
        ]
    },
    {
        "id": 2,
        "name": "ivysaur",
        "types": [
            "grass",
            "poison"
        ]
    },
    {
        "id": 3,
        "name": "venusaur",
        "types": [
            "grass",
            "poison"
        ]
    }


### Save Pokemon 
`POST /api/v1/pokemons`

#### Body
    Content-Type: application/json
    
    {
        "nickName": "fueguito",
        "species": "charmander"
    }

### Response
    {
        "id": 1,
        "nickName": "fueguito",
        "species": "charmander",
        "baseAttack": 11,
        "baseDefence": 2,
        "baseHealth": 14,
        "_links": {
            "self": {
                "href": "http://localhost:8080/api/v1/pokemons/fueguito"
            }
        }
    }

### Get saved pokemon by nickname
`GET /api/v1/pokemons/fueguito`

### Response
    {
        "number": 4,
        "nickName": "fueguito",
        "species": "charmander",
        "attack": 11,
        "defence": 2,
        "health": 14,
        "types": [
            "fire"
        ],
        "_links": {
            "self": {
                "href": "http://localhost:8080/api/v1/pokemons/fueguito"
            }
        }
    }

### Get saved pokemons by species
`GET /api/v1/pokemons?species=species&page=0&size=10`

### Response
    {
        "_embedded": {
            "pokemonList": [
                {
                    "id": 1,
                    "nickName": "fueguito",
                    "species": "charmander",
                    "baseAttack": 7,
                    "baseDefence": 9,
                    "baseHealth": 4,
                    "_links": {
                        "self": {
                            "href": "http://localhost:8080/api/v1/pokemons/fueguito"
                        }
                    }
                },
                {
                    "id": 2,
                    "nickName": "fueguito2",
                    "species": "charmander",
                    "baseAttack": 13,
                    "baseDefence": 13,
                    "baseHealth": 6,
                    "_links": {
                        "self": {
                            "href": "http://localhost:8080/api/v1/pokemons/fueguito2"
                        }
                    }
                }
            ]
        },
        "_links": {
            "self": {
                "href": "http://localhost:8080/api/v1/pokemons?species=charmander&page=0&size=10"
            }
        },
        "page": {
            "size": 10,
            "totalElements": 2,
            "totalPages": 1,
            "number": 0
        }
    }