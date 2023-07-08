describe('HTTPRequest', () => {
  it('GET Call', () => {
    cy.request(
      'GET',
      'https://f51716f3-6393-4362-ac46-7e3ecffa5f7e.mock.pstmn.io/get?test=%E6%B8%AC%E8%A9%A6%E7%92%B0%E5%A2%83'
    )
      .its('status')
      .should('equal', 200)
  })
})
