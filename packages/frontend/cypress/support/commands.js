// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })

//POST成功API
Cypress.Commands.add('setupIntercept', () => {
  cy.intercept(
    'POST',
    'https://001f08b9-acb7-4c3a-a54f-a9254b7e8e55.mock.pstmn.io/createroom',
    (req) => {
      req.body = {
        roomName: '一起玩富饒之城',
        userName: '陳XX',
        userImage: 'imageName1'
      }
      req.reply({
        createTime: '2023-07-04T19:29:54.001Z',
        status: 'OK',
        msg: '',
        room: {
          roomId: 'sxsxs111',
          roomName: '一起玩富饒之城',
          holderId: 'player1',
          holderName: '陳XX',
          users: [
            {
              userId: 'player1',
              userImage: 'imageName1',
              userName: '陳XX'
            }
          ],
          status: 'OPEN',
          totalUsers: 1
        }
      })
    }
  ).as('createRoomRequest')
})

//POST失敗API
Cypress.Commands.add('setupInterceptWithError', () => {
  cy.intercept(
    'POST',
    'https://001f08b9-acb7-4c3a-a54f-a9254b7e8e55.mock.pstmn.io/createroom',
    (req) => {
      req.reply({
        statusCode: 500, // Simulate a server error
        body: 'Error occurred'
      })
    }
  ).as('errorRequest')
})
