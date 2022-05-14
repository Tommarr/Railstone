Stream.of(
Block.createCuboidShape(11, 0, 0, 16, 3, 16),
Block.createCuboidShape(0, 0, 0, 5, 3, 16),
Block.createCuboidShape(12, 3, 1, 15, 9, 4),
Block.createCuboidShape(1, 3, 1, 4, 9, 4),
Block.createCuboidShape(12, 3, 12, 15, 11, 15),
Block.createCuboidShape(1, 3, 12, 4, 11, 15),
Block.createCuboidShape(-1, 11, 12, 17, 16, 16),
Block.createCuboidShape(12, 12, 16, 15, 15, 17),
Block.createCuboidShape(1, 12, 16, 4, 15, 17),
Block.createCuboidShape(12, 7, 2, 15, 10, 8),
Block.createCuboidShape(12, 8, 5, 15, 11, 11),
Block.createCuboidShape(12, 9, 8, 15, 12, 13),
Block.createCuboidShape(1, 8, 5, 4, 11, 11),
Block.createCuboidShape(1, 7, 2, 4, 10, 8),
Block.createCuboidShape(1, 9, 8, 4, 12, 13)
).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();