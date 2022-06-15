// functionalities_spec.js created with Cypress
//
// Start writing your Cypress tests below!
// If you're unfamiliar with how Cypress works,
// check out the link below and learn how to write your first test:
// https://on.cypress.io/writing-first-test
/* ==== Test Created with Cypress Studio ==== */
it("Testing team page renders correctly", function () {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit("http://localhost:3000/");
  cy.get('[href="/teams"]').click();
  cy.get(".content-table > :nth-child(2) > :nth-child(1)").click({
    force: true,
  });
  /* ==== End Cypress Studio ==== */
});

it("Testing buying a ticket unauthenticated", function () {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit('http://localhost:3000/');
  cy.get('[href="/tickets"]').click();
  cy.get(':nth-child(2) > .standard-button-red').click({force: true} );
  cy.get(':nth-child(1) > .standard-button-red').click({force: true} );
  cy.url().should('include', '/login')
  /* ==== End Cypress Studio ==== */
});

it("Testing live game websockets functionallity", function () {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit('http://localhost:3000/');
  cy.get('[href="/liveSimulation"]').click();
  cy.get(':nth-child(1) > select').select('Cleveland Cavaliers');
  cy.get(':nth-child(3) > select').select('Brooklyn Nets');
  cy.get('input').clear();
  cy.get('input').type('20');
  cy.get('.live-button').click();
  /* ==== End Cypress Studio ==== */
});



