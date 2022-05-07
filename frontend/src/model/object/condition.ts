declare namespace Condition {
  interface Common {
    name: string;
    pageSize: number;
    pageNum: number;
  }
  interface User {
    pageSize: number;
    pageNum: number;
    name: string;
    gander: boolean;
    positionId: number;
    departmentId: number;
  }
}
