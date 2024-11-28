# article-enricher

This service classifies article types by making API calls to the enrichment API.

### Required Environment variables
| Environment Variable  | Description                                                                  |
|-----------------------|------------------------------------------------------------------------------| 
| POSTGRES_HOST         | The hostname of the postgres database                                        |
| POSTGRES_PORT         | The port of the postgres database                                            |
| POSTGRES_DB_NAME      | The name of the db for the postgres database                                 |
| POSTGRES_USERNAME     | Postgres username for access. Should contain write permissions               |
| POSTGRES_PASSWORD     | Postgres password for authentication                                         |
| MONGO_HOST            | The hostname of the mongodb database                                         |
| MONGO_PORT            | The port of the mongodb database                                             |
| MONGO_USERNAME        | The username for access. Should container write permissions                  |
| MONGO_PASSWORD        | Mongodb password for authentication                                          |
| MONGO_DB_NAME         | Mongodb Database name                                                        |
| MONGO_AUTH_DB         | **Optional** Authentication database name for mongodb. By default is "admin" |
| ARTICLE_ENRICHER_HOST | url of the article enricher api. Example: "http://localhost:8000"            |

