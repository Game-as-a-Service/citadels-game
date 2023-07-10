// eslint-disable-next-line no-undef
describe('HTTPRequest', () => {
  // eslint-disable-next-line no-undef
  it('GET Call', () => {
    // eslint-disable-next-line no-unused-expressions, no-undef
    cy.request('GET', 'https://postman-echo.com/get?msg=hello')
      .its('status')
      .should('equal', 200)
  })
})
