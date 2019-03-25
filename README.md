Race Time API
========
Sistema calculo de resultados de corrida.

Funcionamento
------------------
* Um arquivo de log de corrida deve ser enviado para o endpoint especificado (abaixo). Uma vez recebido o arquivo (válido), o sistema irá processar as informações, montando os dados da corrida e deixando disponível para consumo (outro endpoint). As informações disponíveis serão sempre do ultimo arquivo de log enviado.


Regras
------------------

* Não é necessário cadastrar os pilotos. Pois eles serão registrados no sistema a medida em que o sistema carrega uma volta do arquivo de log.
* Uma corrida posseu 3 status: OPEN, FINISHED e CLOSE. 
OPEN: Siginifica que ainda não há vencedor (4 voltas determina o vencedor).
FINISHED: Significa que já há um vencedor. Porém ainda há pilotos a terminar a prova (4 voltas).
CLOSE: Todos os corredores completaram as 4 voltas. Corrida encerrada.
 

Informações técnicas
------------------

* O sistema foi criado utilizando Java (java version "10.0.2").
* Foi utilizado Spring Boot
* Foi utilizado conceitos de microserviço (no caso só existe este serviço).
* O sistema foi desenvolvido TDD (https://pt.wikipedia.org/wiki/Test_Driven_Development)
* O sistema foi desenvolvido utilizando Clean Architecture (https://stackoverflow.com/tags/clean-architecture/info)
* O sistema utiliza Lombok. Para desenvolvimento é necessário instalar o pluguin na ide (caso contrário haverá erros de compilação). Mais informações: https://projectlombok.org/

Pendências, Backlogs e débitos técnicos
------------------
* Implementar telas (angular - outro repositório)
* Não foram tratados erros nem caminhos alternativos (fallback)
* Nem todos os cenários de endtoend foram realizados. 
* Pilotos que não terminam a prova não são classificados em ordem de chegada.
* Documentar a API (Swagger - https://swagger.io/)
* Testes unitários na camada de controller
* Salvar dados num BD
* Exibir tempo entre o ganhador e os outros colocados.


Carregamento de Arquivo
------------------
* O arquivo a ser carregado deve respeitar o exemplo abaixo. Note que há 4 padrões de linhas. A diferença entre elas são espaços e tabulações.
* A primeira linha DEVE SER DESCONSIDERADA.

```text
Hora                               Piloto             Nº Volta   Tempo Volta       Velocidade média da volta
23:49:08.277      038 – F.MASSA                           1		1:02.852                        44,275
23:49:11.075      002 – K.RAIKKONEN                       1             1:04.108                        43,408
23:49:30.976      015 – F.ALONSO                          1		1:18.456			35,47
23:51:18.576      033 – R.BARRICHELLO		          3		1:03.716                        43,675

```

* Exemplo de chamada do endpoint

```
curl -X POST \
  http://localhost:8081/racetime/log \
  -H 'Postman-Token: 3dfbec2a-5264-4c0a-a0b6-1a09bd06986b' \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F logFile=undefined
```

* Resposta

```
OK!
```

Exibição de Resultados (ranking)
------------------
* Para exibir o resultado, o seguinte end point deve ser acessado

```
curl -X GET \
  http://localhost:8081/racetime/race \
  -H 'Postman-Token: 2b86ff61-ab43-4bbf-ae82-8d57f61ad14c' \
  -H 'cache-control: no-cache'
```

* Resposta

```
{
    "id": null,
    "open": "23:49:08.277",
    "finish": "23:52:17.003",
    "close": null,
    "status": "FINISHED",
    "bestLap": {
        "number": 3,
        "time": "23:51:14.216",
        "duration": "PT1M2.769S",
        "averageSpeed": 44.334,
        "pilot": {
            "id": 38,
            "nome": "F.MASSA"
        }
    },
    "worstLap": {
        "number": 1,
        "time": "23:52:01.796",
        "duration": "PT3M31.315S",
        "averageSpeed": 13.169,
        "pilot": {
            "id": 11,
            "nome": "S.VETTEL"
        }
    },
    "pilots": [
        {
            "id": 38,
            "nome": "F.MASSA",
            "duration": "PT4M11.578S",
            "average": 44.24575,
            "laps": [
                {
                    "number": 1,
                    "time": "23:49:08.277",
                    "duration": "PT1M2.852S",
                    "averageSpeed": 44.275,
                    "best": false,
                    "worst": false
                },
                {
                    "number": 2,
                    "time": "23:50:11.447",
                    "duration": "PT1M3.17S",
                    "averageSpeed": 44.053,
                    "best": false,
                    "worst": true
                },
                {
                    "number": 3,
                    "time": "23:51:14.216",
                    "duration": "PT1M2.769S",
                    "averageSpeed": 44.334,
                    "best": true,
                    "worst": false
                },
                {
                    "number": 4,
                    "time": "23:52:17.003",
                    "duration": "PT1M2.787S",
                    "averageSpeed": 44.321,
                    "best": false,
                    "worst": false
                }
            ]
        },
        {
            "id": 2,
            "nome": "K.RAIKKONEN",
            "duration": "PT4M15.153S",
            "average": 43.627250000000004,
            "laps": [
                {
                    "number": 1,
                    "time": "23:49:11.075",
                    "duration": "PT1M4.108S",
                    "averageSpeed": 43.408,
                    "best": false,
                    "worst": true
                },
                {
                    "number": 2,
                    "time": "23:50:15.057",
                    "duration": "PT1M3.982S",
                    "averageSpeed": 43.493,
                    "best": false,
                    "worst": false
                },
                {
                    "number": 3,
                    "time": "23:51:19.044",
                    "duration": "PT1M3.987S",
                    "averageSpeed": 43.49,
                    "best": false,
                    "worst": false
                },
                {
                    "number": 4,
                    "time": "23:52:22.12",
                    "duration": "PT1M3.076S",
                    "averageSpeed": 44.118,
                    "best": true,
                    "worst": false
                }
            ]
        }
    ]
}


```

