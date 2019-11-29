# Android-Case


## Eu como caminhoneiro gostaria de ter um aplicativo que

- Seja possível inserir uma localização inicial
- Seja possível inserir uma localização final
- Seja possível inserir o número de eixos do meu veículo
- Seja possível inserir o consumo médio de combustível (Diesel) do meu veículo
- Seja possível inserir o valor atual do Diesel
- Que o aplicativo calcule a rota entre o ponto inicial e o ponto final
- Que o aplicativo disponibilize dados sobre a duração da viagem, número de pedágios, valor mínimo do frete baseado na tabela da ANTT para diferentes tipos de carga

## O que deve conter nesse aplicativo : 

- Permitir que o usuário informe o número de eixos do caminhão dele ( mínimo 2, máximo 9)
- Permitir que o usuário informe o valor de consumo médio (Km/litro) de combustível(diesel)
- Permitir que o usuário escolha a sua origem
- Permitir que a origem seja capturada pelo gps do dispositivo
- Permitir que o usuário escolha um destino
- Exiba o resultado da api para o usuário
- Se possível, permitir que usuário tenha acesso a seu histórico de buscas de rotas e valores

## Orientações para a construção do case:

Nós da TruckPad temos uma API que disponibiliza os dados sobre rotas, preços, números de pedágios, consumo médio de combustível e outras coisas que permeiam a rotina do nosso usuário final, o caminhoneiro. Todos os dados que são requiridos na aplicação são disponibilizados por nossas APIS

Temos o nosso site [Recalcula Frete](https://www.recalculafrete.com.br/) que pode ser usado de apoio para a implementação do case.

Apis de Apoio:

- [https://geo.api.truckpad.io/v1/route](https://geo.api.truckpad.io/v1/route)

Retorna informações sobre distância, tempo de viagem, consumo de combustível, custo total da viagem e  número de pedágios.

Exemplo de um request :
```json 
POST https://geo.api.truckpad.io/v1/route
{
    "places": [{
        "point": [
            -46.68664,
            -23.59496
        ]
    },{
        "point": [
            -46.67678,
            -23.59867
        ]
    }],
    "fuel_consumption": 5,
    "fuel_price": 4.4
}
```
Exemplo de resposta ->

```json 
RESPONSE - > {
    "points": [
        {
            "point": [
                -46.68664,
                -23.59496
            ],
            "provider": "Provided"
        },
        {
            "point": [
                -46.67678,
                -23.59867
            ],
            "provider": "Provided"
        }
    ],
    "distance": 1962,
    "distance_unit": "meters",
    "duration": 583,
    "duration_unit": "seconds",
    "has_tolls": false,
    "toll_count": 0,
    "toll_cost": 0,
    "toll_cost_unit": "R$",
    "route": [
        [
            [
                -46.68662,
                -23.59504
            ],
            [
                -46.6881,
                -23.59571
            ],
            [
                -46.68701,
                -23.59613
            ],
            [
                -46.68546,
                -23.59575
            ],
            [
                -46.67528,
                -23.60051
            ],
            [
                -46.67486,
                -23.59966
            ],
            [
                -46.6768,
                -23.59871
            ]
        ]
    ],
    "provider": "Maplink",
    "cached": true,
    "fuel_usage": 0.39,
    "fuel_usage_unit": "liters",
    "fuel_cost": 1.73,
    "fuel_cost_unit": "R$",
    "total_cost": 1.73
} 
```
Após pegar os dados da rota, usamos outra api para calcular o preço por alguns tipos de carga que mais utilizamos em nossa plataforma
- [ https://tictac.api.truckpad.io/v1/antt_price/all]( https://tictac.api.truckpad.io/v1/antt_price/all)

```json 
POST - https://tictac.api.truckpad.io/v1/antt_price/all
{"axis":2,"distance":2976.087,"has_return_shipment":true}
```
```json
RESPONSE - > {
    "frigorificada": 3987.96,
    "geral": 5654.57,
    "granel": 5595.04,
    "neogranel": 5059.35,
    "perigosa": 3571.3
}
```

## Instruções para a realização do case 
- Faça um **fork** desse repositório;
- Crie uma **branch** com o **seu nome**;
- Depois que terminar suas implementações, faça um **Pull Request** para o branch **master**;

## Recomendações para a realização do case
- O código poderá ser feito em Java, mas será um diferencial se você realizar ele em Kotlin
- Testes unitários não são obrigatórios, mas será um diferencial se o projeto conter testes
- Sugerimos usar algum tipo de arquitetura ( MVVM, MVP, MVI ou qual você achar melhor)
- Nós da TruckPad estaremos a disposição em caso de dúvidas, então não deixe de nos contactar

Boa sorte
 

