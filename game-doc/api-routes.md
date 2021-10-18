# API routes

All routes use JSON for data encoding.
Refer to the [OpenAPI specification](../openapi.yml) for more details.

- GET /maps => array of map names [200 OK]
- POST /maps => map instance [201 CREATED]
- GET /maps/{mapName} => map instance [200 OK], error instance [404 NOT FOUND]
- GET /maps/{mapName}/replays?sortBy=score&limit=5 => array of 5 best player names who registered a replay for the specified map [200 OK], error instance [404 NOT FOUND]
- POST /maps/{mapName}/replays => replay instance [201 OK], [204 NO CONTENT], error instance [404 NOT FOUND], error instance [400 BAD REQUEST]
- GET /maps/{mapName}/replays/{playerName} => replay instance [201 OK], error instance [404 NOT FOUND]
