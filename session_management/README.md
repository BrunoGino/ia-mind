- GET /sessions/{id} 
> Traz os metadados de uma sessão no formato JSON com as propriedades:
1. id  - uma chave hash do tipo string
2. student  - um objeto com as propriedades id (uma chave hash), ra (string), fullName (string)
3. institution - um objeto com as propriedades id (chave hash), name (string), city (string),state (string) e country (string)
4. dateTime - um timestamp no formato string
5. professional - um objeto com as propriedades id (hash), fullName (string