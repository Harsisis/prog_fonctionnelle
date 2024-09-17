
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

const simulerTir = (equipe: 'A' | 'B'): [boolean, 'A' | 'B'] => {
    const tirMarque = Math.random() < 0.5; // 50% chance de marquer
    return [tirMarque, equipe];
  };

const mettreAJourScore = (score: Score, equipe: 'A' | 'B', marque: boolean): Score => {
    return {
      equipeA: equipe === 'A' && marque ? score.equipeA + 1 : score.equipeA,
      equipeB: equipe === 'B' && marque ? score.equipeB + 1 : score.equipeB
    };
  };

const ajouterAuHistorique = (historique: Historique, tirNumber: number, score: Score, equipe: 'A' | 'B', marque: boolean): Historique => {
    const detail = `Tir ${tirNumber} | Score : ${score.equipeA}/${score.equipeB} (équipe A : ${equipe === 'A' && marque ? '+1' : '0'}, équipe B : ${equipe === 'B' && marque ? '+1' : '0'})`;
    return [...historique, { tirNumber, score, detail }];
  };

const afficherHistorique = (historique: Historique): void => {
    historique.forEach(tir => {
      console.log(tir.detail);
    });
  };

const simulerSeanceRecursive = (
    tirNumber: number,
    score: Score,
    historique: Historique,
    nombreTirsPourGagner: number
  ): Historique => {
    if (score.equipeA >= nombreTirsPourGagner || score.equipeB >= nombreTirsPourGagner) {
      return historique; // Fin de la récursion lorsque l'une des équipes a gagné
    }
  
    // Choisir une équipe aléatoirement pour ce tir
    const equipe: 'A' | 'B' = Math.random() < 0.5 ? 'A' : 'B';
    const [marque, equipeTir] = simulerTir(equipe);
  
    // Mettre à jour le score de manière immuable
    const nouveauScore = mettreAJourScore(score, equipeTir, marque);
  
    // Ajouter ce tir à l'historique
    const nouvelHistorique = ajouterAuHistorique(historique, tirNumber, nouveauScore, equipeTir, marque);
  
    // Appel récursif pour le prochain tir
    return simulerSeanceRecursive(tirNumber + 1, nouveauScore, nouvelHistorique, nombreTirsPourGagner);
  };

  
const simulerSeanceTirs = (nombreTirsPourGagner: number): void => {
    const scoreInitial: Score = { equipeA: 0, equipeB: 0 };
    const historiqueInitial: Historique = [];
  
    // Lancer la simulation récursive
    const historiqueFinal = simulerSeanceRecursive(1, scoreInitial, historiqueInitial, nombreTirsPourGagner);
  
    // Afficher l'historique final
    afficherHistorique(historiqueFinal);
  
    // Déterminer et afficher le vainqueur
    const dernierScore = historiqueFinal[historiqueFinal.length - 1].score;
    if (dernierScore.equipeA >= nombreTirsPourGagner) {
      console.log('Victoire : Equipe A');
    } else if (dernierScore.equipeB >= nombreTirsPourGagner) {
      console.log('Victoire : Equipe B');
    }
  };

  
simulerSeanceTirs(5);
