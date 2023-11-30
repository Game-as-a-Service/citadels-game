export type CreateRoom = {
  createTime: string
  status: string
  msg: string
  rooms: Record<string, create_room>
}

export type create_room = {
  roomId: string
  roomName: string
  holderId: string
  holderName: string
  users: Array<{
    userId: string
    userName: string
    userImage: string
  }>
  status: string
  totalUsers: number
}

export type SpecificRoom = {
  searchTime: string
  status: string
  msg: string
  rooms: Record<string, specific_room>
}

export type specific_room = {
  roomId: string
  roomName: string
  createTime: string
  status: string
  holderName: string
  holderId: string
  totalUsers: number
  users: Array<{
    userId: string
    userName: string
    userImage: string
  }>
}


interface BuildCard {
  name: string;
  coins: number;
  color: "BLUE" | "YELLOW" | "PURPLE"; 
}

export interface PlayerView {
  id: string;
  name: string;
  imageName: string;
  coins: number;
  buildCards: BuildCard[];
  characterCard: null | string; 
  hasCrown: boolean;
}

interface CharacterCardView {
  sequence: number;
  name: string;
}

export interface GameData {
  id: string;
  roomId: string;
  name: string;
  status: string;
  createTime: string;
  playerViews: PlayerView[];
  characterCardViews: CharacterCardView[];
  buildCardViews: BuildCard[];
}

