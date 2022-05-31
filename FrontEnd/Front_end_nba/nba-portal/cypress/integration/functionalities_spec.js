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

it("Testing clicking on see tickets button unauthenticated", function () {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit('http://localhost:3000/');
  cy.get('[href="/games"]').click();
  cy.get(':nth-child(5) > :nth-child(2)').click({force: true});
  cy.get('.button-big').click();
  cy.location('pathname').should('match', /\/login$/);
  /* ==== End Cypress Studio ==== */
});
