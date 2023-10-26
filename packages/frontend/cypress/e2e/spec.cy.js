describe('Main Page Test', () => {
  it('Display game lobby', () => {
    cy.viewport(1920, 1080)
    cy.intercept('GET', '**/games', {
      success: true,
      fixture: 'roomList.json'
    }).as('getRooms')
    cy.visit('http://localhost:3001/rooms')
    cy.get('.room-list')
  })
})
