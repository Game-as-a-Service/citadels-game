describe('HTTPRequest', () => {
  it('GET Call', () => {
    cy.request(
      'GET',
      'https://001f08b9-acb7-4c3a-a54f-a9254b7e8e55.mock.pstmn.io/get?msg=hello'
    )
      .its('status')
      .should('equal', 200)
  })
})
