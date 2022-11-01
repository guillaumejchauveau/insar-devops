import {getMapNames} from '../mocks/restMocks';

describe('E2E Tests', () => {
  beforeEach(() => {
    cy.server();
    cy.fixture('maps.json')
      .then(fakedata => {
        getMapNames(fakedata);
      })
      .visit('/')
      .wait('@getMapNames');

    cy.intercept('GET', 'api/maps/grass', {fixture: 'mapGrass.json'});
    cy.intercept('POST', 'api/maps', {fixture: 'randomMap.json'});
  });

  it('should visit the initial project page', () => {
    cy.visit('/');
    cy.contains('Oyun');
  });

  it('should have the ui', () => {
    cy.visit('/');
    // changing player name
    cy.get('#playername')
      .clear()
      .type('pseudo');
    cy.contains('Random map').click();
    // checking all the ui elements
    cy.contains('Undo');
    cy.contains('Redo');
    cy.contains('pseudo');
    cy.contains('Terminer la partie');
    cy.contains('Best scores');
  });

  it('should create a game with a new random map', () => {
    cy.visit('/');
    cy.contains('Random map').click();
    cy.url().should('include', '/game');
  });

  it('should change the player name', () => {
    cy.visit('/');
    cy.get('#playername')
      .clear()
      .type('pseudo')
      .should('have.value', 'pseudo');
  });

  it('should end the game and redirect to menu', () => {
    cy.visit('/');
    cy.contains('grass').click();

    cy.get('#2\\ 2').click();

    cy.on('window:alert', (txt) => {
      expect(txt).to.contains('Partie terminée');
    });

    cy.contains('Oyun');
  });

  it('should show the correct score on the grass map', () => {
    cy.visit('/');
    cy.contains('grass').click();

    cy.get('#2\\ 2').trigger('mouseenter');
    cy.get('#2\\ 2').contains('6');
  });

  it('should show the correct score on the random map', () => {
    cy.visit('/');
    cy.contains('Random map').click();

    cy.get('#4\\ 3').trigger('mouseenter');
    cy.get('#4\\ 3').contains('6');
    cy.get('#4\\ 2').contains('5');
    cy.get('#3\\ 2').contains('5');
  });
});
