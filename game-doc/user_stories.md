# User stories

## Ecran d'accueil
- la liste des cartes est affichée
- L'utilisateur peut lancer une partie en choisissant une carte de jeu parmis la liste des cartes disponibles, ou aléatoirement.

## Ecran de jeu
- la carte de jeu est affichée sous forme 2D isométrique. Elle est constituée de 10x10 cases. Il existe plusieurs type de cases: herbe, foret, lac.
- le score est affiché avec le prochain palier de niveau. **Le score affiché doit être strictement inférieur au palier.**
- l'inventaire des batiments disponibles est affiché. Il existe plusieurs types de batiment: maison, éolienne, cirque, fontaine. Le nombre d'unités disponible par type de batiment est affiché. **Les nombres d'unités disponibles doivent être positifs ou nuls.**
- si tous les types de batiments dans l'inventaire sont nuls, la partie se termine.
- L'utilisateur peut jouer un coup en ajoutant un batiment sur la carte parmis ceux de son inventaire. Il sélectionne d'abord le type de batiment, puis la case de destination. Un coup rapporte des points relatifs, selon le type de batiment choisi, les types des cases adjacentes (coté et diagonale) dans un certain rayon ainsi que le type du batiment sur les cases s'il y en a. Les points rapportés par les cases dans le rayon, positifs ou négatifs, sont affichés lorsque l'utilisateur s'apprète à choisir la case. Le coup réduit le nombre d'unités disponibles du batiment dans l'inventaire de 1. Les points du coup sont ajoutés au score. Si le score dépasse le palier de niveau, le niveau augmente, ajoutant une unité disponible à tous les batiments de l'inventaire, et augmentant le palier de prochain niveau. Ce processus est répété jusqu'à ce que le score soit inférieur au palier de niveau. **La case de destination doit être de type prairie. Il ne doit pas y avoir un batiment déjà présent sur la case sélectionnée. Le nombre d'unités disponibles du type de batiment sélectionné doit être strictement positif. Les points du coup doivent respecter les regles decrites ci-dessous.**
- l'utilisateur peut cliquer sur le bouton classement pour afficher la liste des meilleurs scores des joueurs sur la carte.
- L'utilisateur peut cliquer sur le bouton annuler pour annuler un coup. La carte, le score, le palier de niveau et l'inventaire sont mis à jour en conséquence. **L'état de la partie doit être exactement le même.**
- L'utilisateur peut cliquer sur le bouton refaire pour refaire un coup qu'il vient d'annuler. La carte, le score, le palier de niveau et l'inventaire sont mis à jour en conséquence.
- L'utilisateur peut cliquer sur le bouton fin de partie pour terminer la partie.
- L'utilisateur peut choisir un pseudo afin d'identifier le score de sa partie. Lorsque que l'utilisateur clique sur le champ texte "name", il peut changer son pseudo qui sera affiché en jeu (en haut à droite sur l'interface utilisateur, en dessous du nom de la carte).
- Lorsque la partie se termine, le score de la partie est enregistré dans le classement avec le pseudo de l'utilisateur. L'utilisateur peut retourner sur l'écran d'accueil.

## Calcul des points d'un coup
- maison: `6 + 5 * foret + -1 * maison + 8 * fontaine` // rayon 1
- cirque: `8 + 15 * maison * -25 * cirque` // rayon 3
- fontaine: `6 + 8 * maison + 6 * cirque` // rayon 1
- éolienne: `15 + -8 * maison + -4 * foret + 10 * lac` // rayon 2
