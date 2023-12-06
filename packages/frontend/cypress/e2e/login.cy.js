describe('Login Page Test', () => {
  it('save user name', () => {
    cy.visit('http://localhost:3001/login');
    cy.get('#name').type('Snowman');
    cy.get('.login_button').click();
    cy.url().should('include', '/rooms');
    cy.window().then((win) => {
      // 使用 win.localStorage.getItem() 來獲取 localStorage 的值
      expect(win.localStorage.getItem('userName')).to.equal('Snowman');
    });
  })
})