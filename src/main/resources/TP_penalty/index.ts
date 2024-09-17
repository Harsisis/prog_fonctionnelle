type Score = {
  equipeA: number;
  equipeB: number;
};

type Tir = {
  tirNumber: number;
  score: Score;
  detail: string;
};

type Historique = Tir[];

const simulerTir = (equipe: "A" | "B"): [boolean, "A" | "B"] => {
  const tirMarque = Math.random() < 0.5;
  return [tirMarque, equipe];
};

const mettreAJourScore = (
  score: Score,
  equipe: "A" | "B",
  marque: boolean
): Score => {
  return marque
    ? {
        equipeA: equipe === "A" ? score.equipeA + 1 : score.equipeA,
        equipeB: equipe === "B" ? score.equipeB + 1 : score.equipeB,
      }
    : score;
};

const ajouterAuHistorique = (
  historique: Historique,
  tirNumber: number,
  score: Score,
  equipe: "A" | "B",
  marque: boolean
): Historique => {
  const detail = `Tir ${tirNumber} | Score : ${score.equipeA}/${
    score.equipeB
  } (équipe A : ${equipe === "A" && marque ? "+1" : "0"}, équipe B : ${
    equipe === "B" && marque ? "+1" : "0"
  })`;
  return [...historique, { tirNumber, score, detail }];
};

const afficherHistorique = (historique: Historique): void => {
  historique.forEach((tir) => {
    console.log(tir.detail);
  });
};

// #################################

const nombreTirsPourGagner = 5;

const simulerSeanceTirs = (): void => {
  let score: Score = { equipeA: 0, equipeB: 0 };
  let historique: Historique = [];

  for (let tirNumber = 1; tirNumber <= 5; tirNumber++) {
    const equipe: "A" | "B" = Math.random() < 0.5 ? "A" : "B";
    const [marque, equipeTir] = simulerTir(equipe);
    score = mettreAJourScore(score, equipeTir, marque);
    historique = ajouterAuHistorique(
      historique,
      tirNumber,
      score,
      equipeTir,
      marque
    );
  }

  afficherHistorique(historique);

  console.log(`Victoire : Equipe ${score.equipeA === nombreTirsPourGagner ? 'A' : 'B'}`)
};

simulerSeanceTirs();
