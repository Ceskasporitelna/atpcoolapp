
# Big6 app

Database
```
docker -H localhost:2375 run --name bigsixdb -e POSTGRES_DB=bigsix -e POSTGRES_USER=bigsix -e POSTGRES_PASSWORD=big6 -p 5432:5432 -d postgres
```


Build and run a Docker container
```
docker -H localhost:2375 build -t bigsix .

docker -H localhost:2375 run --name bigsix -p 80:8080 -d bigsix
```


## Saifu hook

### Request
```
POST http://atpagents.australiaeast.cloudapp.azure.com/bigsix/api/v1/saifu
```

**Body**
```json
{
    "amount": 1000000,
    "trnId": "testovaciid"
}
```

### Response
```
STATUS 204
```

## Get list of transactions

### Request
```
GET http://atpagents.australiaeast.cloudapp.azure.com/bigsix/api/v1/transactions
```

### Response
```json
[
    {
        "trnid": "testovaciid",
        "amount": "820218288441456039277933433265797065465035737435823459282573995782557164588708057190397420133878655412274599489413734011971318332230426414493049846118589",
        "currency": "CZK",
        "expdate": "2030-01-01",
        "mpan": "mpan",
        "authdate": "2017-09-16",
        "status": "status"
    },
    {
        "trnid": "SGYUSGYSBUSJSH26867632",
        "amount": "2669303533176933966437346847241887389751866191163149564133877024491601808497421415568818912690888948205709540232767240416689650580678983686862603504981203",
        "currency": "CZK",
        "expdate": "2030-01-01",
        "mpan": "mpan",
        "authdate": "2017-09-16",
        "status": "status"
    }
]
```

## Get list of items for transaction

### Request
```
GET http://atpagents.australiaeast.cloudapp.azure.com/bigsix/api/v1/transactions/{trnid}
```

### Response
```json
[
    {
        "id": "2a5f473e-93ab-4400-a369-2f899a52b59e",
        "trnid": "testovaciid",
        "type": "0213101",
        "price": "2177680483544747880664146868440818565015413429017715166356908919771215192016594672129186766887360071214588272951436826848552230202315710792738995023394029",
        "name": "Pilsner Urqell, svÄ›tlĂ˝ leĹľĂˇk, plech 0,5l"
    },
    {
        "id": "b887d25c-0ce0-4943-a4d8-e1690b4f0394",
        "trnid": "testovaciid",
        "type": "0114301",
        "price": "2632292731038305740512190874075520321412534205207860907874791793754497814289764928830289675769809231618328758124387788134639261931654348920803754943980423",
        "name": "Tatra TrvanlivĂ© polotuÄŤnĂ© mlĂ©ko 1,5%, 1l"
    }
]
```

## Retrieve sum of trn. items for each region

### Request
```
GET http://atpagents.australiaeast.cloudapp.azure.com/bigsix/api/v1/transactions/{trnid}/baskets
```

### Response
```json
[
    {
        "name": "Main",
        "price": "1035382426028036585136216540776677868265268435753940132135669645701846033594350738111680940204193413843782407412347137708279432982671294870995339521321272"
    },
    {
        "name": "PardubickĂ˝ kraj",
        "price": "326577617241108540657370560050960634792013960905364967891500988035971603004371509091660340236768172197509831817289862849531883692909774142844966288241904"
    },
    {
        "name": "OlomouckĂ˝ kraj",
        "price": "2663972509288610840026043664375872091962598052032386969992253694053578349361064406053751068052125340733489685296560499467289844898749307266458358355904926"
    }
]
```

## Decrypt value

### Request
```
POST http://atpagents.australiaeast.cloudapp.azure.com/bigsix/api/v1/decrypt
```

Request body
```json
{
	"data": "1035382426028036585136216540776677868265268435753940132135669645701846033594350738111680940204193413843782407412347137708279432982671294870995339521321272"
}
```


### Response
```json
{
    "value": "165570"
}
```