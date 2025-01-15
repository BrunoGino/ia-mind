#!/bin/bash
table_name_users="iamind_users_table"
hash_key_users_users="id"

awslocal dynamodb create-table \
    --table-name "$table_name_users" \
    --key-schema AttributeName="$hash_key_users",KeyType=HASH \
    --attribute-definitions AttributeName="$hash_key_users",AttributeType=S \
    --billing-mode PAY_PER_REQUEST

echo "DynamoDB table '$table_name_users' created successfully with hash key '$hash_key_users'"

table_name_sessions="iamind_session_table"
hash_key_sessions="id"

awslocal dynamodb create-table \
    --table-name "$table_name_sessions" \
    --key-schema AttributeName="$hash_key_sessions",KeyType=HASH \
    --attribute-definitions AttributeName="$hash_key_sessions",AttributeType=S \
    --billing-mode PAY_PER_REQUEST

echo "DynamoDB table '$table_name_sessions' created successfully with hash key '$hash_key_sessions'"

echo "Executed init-dynamodb.sh"