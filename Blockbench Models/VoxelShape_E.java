Stream.of(
Block.createCuboidShape(0, 0, 0, 16, 3, 5),
Block.createCuboidShape(0, 0, 11, 16, 3, 16),
Block.createCuboidShape(1, 3, 1, 4, 9, 4),
Block.createCuboidShape(1, 3, 12, 4, 9, 15),
Block.createCuboidShape(12, 3, 1, 15, 11, 4),
Block.createCuboidShape(12, 3, 12, 15, 11, 15),
Block.createCuboidShape(12, 11, -1, 16, 16, 17),
Block.createCuboidShape(16, 12, 1, 17, 15, 4),
Block.createCuboidShape(16, 12, 12, 17, 15, 15),
Block.createCuboidShape(2, 7, 1, 8, 10, 4),
Block.createCuboidShape(5, 8, 1, 11, 11, 4),
Block.createCuboidShape(8, 9, 1, 13, 12, 4),
Block.createCuboidShape(5, 8, 12, 11, 11, 15),
Block.createCuboidShape(2, 7, 12, 8, 10, 15),
Block.createCuboidShape(8, 9, 12, 13, 12, 15)
).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();