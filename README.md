# Programmation fonctionnelle - CADET Gauthier

## TP01 - boutique de fruits

--> run les tests de l'applciation

## TP02 - évaluation de code

Le principal problème de ce code est la modification du stock et des Fruits avec mutation des données.

```ts
let fruitsStock = [
  { id: 1, name: "Pomme", quantity: 10 },
  { id: 2, name: "Poire", quantity: 5 },
  { id: 3, name: "Ananas", quantity: 8 }
];

function addFruitToStock(name: string, quantity: number) {
  const existingProduct = fruitsStock.find((p) => p.name === name);

  if (existingProduct) {
    existingProduct.quantity += quantity;
  } else {
    fruitsStock.push({ id: fruitsStock.length + 1, name, quantity });
  }
}

function deleteFruit(name: string) {
  fruitsStock = fruitsStock.filter((p) => p.name !== name);
  console.log(`${name} deleted from stock`);
}

function showStock() {
  fruitsStock.forEach((fruit) => {
    console.log(`Fruit : ${fruit.name} | Quantity : ${fruit.quantity}`);
  });
}

function sellFruit(name: string, quantity: number) {
  const fruit = fruitsStock.find((p) => p.name === name);

  if (fruit && fruit.quantity >= quantity) {
    fruit.quantity -= quantity;
    console.log(`${quantity} ${name} sold`);
  } else {
    console.log(`Not enough ${name} or unknown fruit`);
  }
}

addFruitToStock("Pomme", 5);
addFruitToStock("Citron", 10);
sellFruit("Ananas", 2);
showStock();
deleteFruit("Ananas");
showStock();

/*
2 Ananas sold
Fruit : Pomme | Quantity : 15
Fruit : Poire | Quantity : 5
Fruit : Ananas | Quantity : 6
Fruit : Citron | Quantity : 10
Ananas deleted from stock
Fruit : Pomme | Quantity : 15
Fruit : Poire | Quantity : 5
Fruit : Citron | Quantity : 10
*/
```

## TP03 - refonte du code
Création de l'entité `Vegetable` et des services associés dans le but de refaire le TP01 en programmation fonctionnelle. 

Ajout d'un test de scénation équivalent à celui du TP01

## TP04 - penalty

Création d'un script TS pour le TP04 pour les penaltys

Voir : `src/main/resources/TP_penalty/index.ts`

### Refonte en java
Refonte du code en ts pour la gestion des penalty en java.

Utilisation des suppliers puis d'un reduce pour executer les actions.

## TP05 - pizzas

1. Lecture des jsons 
2. Ecriture tests + code
