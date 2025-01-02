resource "aws_apigatewayv2_api" "iamind_api_gateway" {
  name          = "iamind_api_gateway"
  protocol_type = "HTTP"

  cors_configuration {
    allow_headers = ["content-type", "x-amz-date", "authorization", "x-api-key", "x-amz-security-token", "x-amz-user-agent"]
    allow_methods = ["*"]
    allow_origins = ["*"]
  }

  fail_on_warnings = true

  tags = local.default_tags

  body = jsonencode({
    openapi = "3.0.1",
    info = {
      title   = "IAMind API Definition",
      version = "0.1"
    },
    paths = {
      "/sessions/{session-id}" = {
        get = {
          operationId = "get-session-metadata",
          parameters = [
            {
              name        = "session-id",
              in          = "path",
              description = "Session id to get metadata for",
              required    = true,
              schema = {
                type = "string"
              }
            }
          ],
          responses = {
            "200" = {
              description = "Successful operation"
              content = {
                "application/json" = {
                  schema = {
                    "$ref" = "#/components/schemas/SessionMetadata"
                  }
                }
              }
            },
            "404" = {
              description = "Session not found"
            }
          }
        }
      },
      "/sessions" = {
        get = {
          operationId = "get-all-my-sessions",
          responses = {
            "200" = {
              description = "Successful operation"
              content = {
                "application/json" = {
                  schema = {
                    type = "array",
                    items = {
                      "$ref" = "#/components/schemas/SessionMetadata"
                    }
                  }
                }
              }
            }
          }
        }
      }
    },
    components = {
      schemas = {
        SessionMetadata = {
          title       = "Session Metadata"
          description = "All information about a session",
          type        = "object",
          properties = {
            id = {
              type = "string"
            },
            student = {
              type = "object",
              properties = {
                id = {
                  type = "string"
                },
                ra = {
                  type = "string"
                },
                fullName = {
                  type = "string"
                },
                institution = {
                  type = "object",
                  properties = {
                    id = {
                      type = "string"
                    },
                    name = {
                      type = "string"
                    },
                    city = {
                      type = "string"
                    },
                    state = {
                      type = "string"
                    },
                    country = {
                      type = "string"
                    }
                  }
                }
              }
            },
            dateTime = {
              type = "string"
            },
            professional = {
              type = "object"
              properties = {
                id = {
                  type = "string"
                },
                fullName = {
                  type = "string"
                }
              }
            }
          }
        }
      }
    }
  })
}
