// admin_spec.js created with Cypress
//
// Start writing your Cypress tests below!
// If you're unfamiliar with how Cypress works,
// check out the link below and learn how to write your first test:
// https://on.cypress.io/writing-first-test
/* ==== Test Created with Cypress Studio ==== */
it('Test logging in as admin', function() {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit('http://localhost:3000/');
  cy.get('.float-right').click();
  cy.get(':nth-child(1) > input').clear();
  cy.get(':nth-child(1) > input').type('nstankov01');
  cy.get('form').click();
  cy.get(':nth-child(2) > input').clear();
  cy.get(':nth-child(2) > input').type('admin');
  cy.get('.button-container > input').click();
  cy.get('[data-testid="navbar"]').should('contain', 'Admin')
  /* ==== End Cypress Studio ==== */
});

it('Test adding a ticket without correct info', function() {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit('http://localhost:3000/');
  cy.get('.float-right').click();
  cy.get(':nth-child(1) > input').clear();
  cy.get(':nth-child(1) > input').type('nstankov01');
  cy.get(':nth-child(2) > input').clear();
  cy.get(':nth-child(2) > input').type('admin');
  cy.get('.button-container > input').click();
  cy.get('[href="/adminPanel"]').click();
  cy.get(':nth-child(3) > .form > form > .button-container > input').click();
  cy.get(':nth-child(1) > select').select('PREMIUM');
  cy.get(':nth-child(3) > .form > form > .button-container > input').click();
  cy.get(':nth-child(3) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(3) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(3) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(3) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(3) > .form > form > .button-container > input').click();
  cy.get(':nth-child(3) > .form').click();
  /* ==== End Cypress Studio ==== */
});

it('Test adding a game without correct info', function() {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit('http://localhost:3000/');
  cy.get('.float-right').click();
  cy.get(':nth-child(1) > input').clear();
  cy.get(':nth-child(1) > input').type('nstankov01');
  cy.get(':nth-child(2) > input').clear();
  cy.get(':nth-child(2) > input').type('admin');
  cy.get('.button-container > input').click();
  cy.get('[href="/adminPanel"]').click();
  cy.get(':nth-child(2) > .form > form > .button-container > input').click();
  cy.get(':nth-child(1) > input').clear();
  cy.get(':nth-child(1) > input').type('22222');
  cy.get(':nth-child(2) > .form > form > .button-container > input').click();
  cy.get(':nth-child(2) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(2) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(2) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(2) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(2) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(2) > .form > form > :nth-child(2) > input').clear();
  cy.get(':nth-child(2) > .form > form > :nth-child(2) > input').type('22222');
  cy.get(':nth-child(2) > .form > form > .button-container > input').click();
  cy.get(':nth-child(2) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(2) > .form > form > :nth-child(2) > input').click();
  cy.get(':nth-child(3) > select').select('17');
  cy.get(':nth-child(2) > .form > form > .button-container > input').click();
  /* ==== End Cypress Studio ==== */
});

it('Test navbar for non admin users', function() {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit('http://localhost:3000/');
  cy.get('.float-right').click();
  cy.get(':nth-child(1) > input').clear();
  cy.get(':nth-child(1) > input').type('test');
  cy.get(':nth-child(2) > input').clear();
  cy.get(':nth-child(2) > input').type('test');
  cy.get('.button-container > input').click();
  cy.get('[data-testid="navbar"]').should('not.contain', 'Admin')
  /* ==== End Cypress Studio ==== */
});
