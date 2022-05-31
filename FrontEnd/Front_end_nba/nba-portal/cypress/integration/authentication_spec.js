// authentication_spec.js created with Cypress
//
// Start writing your Cypress tests below!
// If you're unfamiliar with how Cypress works,
// check out the link below and learn how to write your first test:
// https://on.cypress.io/writing-first-test
/* ==== Test Created with Cypress Studio ==== */
it("Testing logging in", function () {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit("http://localhost:3000/");
  cy.get(".float-right").click();
  cy.get(":nth-child(1) > input").clear();
  cy.get(":nth-child(1) > input").type("nstankov01");
  cy.get(":nth-child(2) > input").clear();
  cy.get(":nth-child(2) > input").type("admin");
  cy.get(".button-container > input").click();
  cy.get("h2").should("contain", "nstankov01");
  /* ==== End Cypress Studio ==== */
});

it("Testing registering for existing username", function () {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit("http://localhost:3000/");
  cy.get(".float-right").click();
  cy.get(".sign-up").click();
  cy.get(":nth-child(1) > input").clear();
  cy.get(":nth-child(1) > input").type("test");
  cy.get(":nth-child(2) > input").clear();
  cy.get(":nth-child(2) > input").type("test");
  cy.get("form").click();
  cy.get(":nth-child(3) > input").clear();
  cy.get(":nth-child(3) > input").type("test");
  cy.get(".button-container > input").click();
  cy.get(".text-error").should("contain", "This username already exists");
  /* ==== End Cypress Studio ==== */
});

it("Testing registering for mismatching password", function () {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit('http://localhost:3000/');
  cy.get('.float-right').click();
  cy.get('.sign-up').click();
  cy.get(':nth-child(1) > input').clear();
  cy.get(':nth-child(1) > input').type('asd');
  cy.get(':nth-child(2) > input').clear();
  cy.get(':nth-child(2) > input').type('1234');
  cy.get(':nth-child(3) > input').clear();
  cy.get(':nth-child(3) > input').type('4321');
  cy.get('.button-container > input').click();
  cy.get(".text-error").should("contain", "Passwords don't match");
  /* ==== End Cypress Studio ==== */
});
