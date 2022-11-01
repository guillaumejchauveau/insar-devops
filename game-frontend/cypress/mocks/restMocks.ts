
export const getMapNames = (response: Array<string>) => {
  cy.route({
      method: 'GET',
      url: 'api/maps',
      response
    }
  ).as('getMapNames');
};
