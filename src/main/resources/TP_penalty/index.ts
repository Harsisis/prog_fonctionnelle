const shootPenalty = (penaltyStatusArray: [number, number][]) => {
  const shootResult = Math.round((Math.random() * 10000) % 3);
  const copiedArray = [...penaltyStatusArray];
  if (shootResult == 1) {
    copiedArray.push([1, 0]);
  } else if (shootResult == 2) {
    copiedArray.push([0, 1]);
  } else {
    copiedArray.push([0, 0]);
  }
  return copiedArray;
};

const displayTurnScore = (
  penaltyStatusArray: [number, number][],
  index: number
) => {
  console.log(
    `Tir ${index + 1} : EquipeA ${penaltyStatusArray[index][0]}, EquipeB ${
      penaltyStatusArray[index][1]
    }`
  );
};

const displayFinalScore = (penaltyStatusArray: [number, number][]) => {
  let sumLeft: number = 0;
  let sumRight: number = 0;
  for (let tuple of penaltyStatusArray) {
    sumLeft += tuple[0];
    sumRight += tuple[1];
  }
  console.log(`EquipeA ${sumLeft}, EquipeB ${sumRight}`);
};



// Execution for x turn ###################################################################

const turnAmount = 5;
let penaltyStatusArray: [number, number][] = [];
for (let i = 0; i < turnAmount; i++) {
  let penaltyResultsArray = shootPenalty(penaltyStatusArray);
  displayTurnScore(penaltyResultsArray, i);
  penaltyStatusArray = penaltyResultsArray;
}
displayFinalScore(penaltyStatusArray);

// ########################################################################################
