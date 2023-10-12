export interface CreateRoom {
    createTime: string;
    status: string;
    msg: string;
    rooms: Record<string, create_room>;
  }

export interface create_room {
    roomId: string;
    roomName: string;
    holderId: string;
    holderName: string;
    users:Array<{
      userId: string;
      userName: string;
      userImage: string;
    }>;
    status: string,
    totalUsers: number;
  }

  export interface SpecificRoom {
    searchTime: string;
    status: string;
    msg: string;
    rooms: Record<string, specific_room>;
  }

  export interface specific_room {
    roomId: string;
    roomName: string;
    createTime: string;
    status: string;
    holderName: string;
    holderId: string;
    totalUsers: number;
    users: Array<{
      userId: string;
      userName: string;
      userImage: string;
    }>;
  }
  
